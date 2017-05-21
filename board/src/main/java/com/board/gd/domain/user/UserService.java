package com.board.gd.domain.user;

import com.board.gd.domain.company.CompanyService;
import com.board.gd.exception.UserException;
import com.board.gd.mail.MailMessage;
import com.board.gd.mail.MailService;
import com.board.gd.utils.DateUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by gd.godong9 on 2017. 4. 3.
 */

@Transactional(readOnly = true)
@Service
public class UserService implements UserDetailsService {
    private static final Date DEFAULT_ROLE_EXPIRED_DATE = new Date(4150537200000L); // 2099년 12월 31일

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private CompanyService companyService;

    private static final BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByEmail(username);
    }

    public List<GrantedAuthority> findRolesByUserId(Long userId) {
        return userRoleRepository.findByUserId(userId).stream()
                .filter(userRole -> !DateUtils.isExpired(userRole.getExpiredAt())) // 만료되지 않은 role만 가져옴
                .map(userRole -> userRole.getRole().name())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = false)
    public UserRole createUserRole(User user, UserRoleType userRole, Date expiredAt) {
        if (Objects.isNull(user)) {
            throw new UserException("Fail createUserRole!");
        }
        return userRoleRepository.save(UserRole.builder()
                .role(userRole)
                .user(user)
                .expiredAt(expiredAt)
                .build());
    }

    @Transactional(readOnly = false)
    public User createUserEmail(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());

        // 유저가 존재하고 인증 안되었을 경우 인증메일 재발송
        if (!Objects.isNull(user) && !user.getEnabled()) {
            sendAuthEmail(user, "auth");
            return user;
        }

        // 유저가 존재할 경우
        if (!Objects.isNull(user)) {
            throw new UserException("이미 가입한 유저입니다. 로그인해주세요.");
        }

        user = userRepository.save(User.builder()
                .name("")
                .email(userDto.getEmail())
                .authUUID(UUID.randomUUID().toString())
                .enabled(false)
                .build());

        sendAuthEmail(user, "auth");

        return user;
    }

    @Transactional(readOnly = false)
    public User updateAuthInfo(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        user.setAuthUUID(UUID.randomUUID().toString());
        user = userRepository.save(user);

        sendAuthEmail(user, "password");

        return user;
    }

    @Transactional(readOnly = false)
    public User create(UserDto userDto) {
        User user = userRepository.save(User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(bcryptEncoder.encode(userDto.getPassword()))
                .profileImg(userDto.getProfileImg())
                .authUUID(UUID.randomUUID().toString())
                .enabled(false)
                .build());

        sendAuthEmail(user, "auth");

        createUserRole(user, UserRoleType.USER, DEFAULT_ROLE_EXPIRED_DATE);

        return user;
    }

    @Transactional(readOnly = false)
    public User update(UserDto userDto) {
        User user = userRepository.findOne(userDto.getId());

        if (!Objects.isNull(userDto.getName())) {
            user.setName(userDto.getName());
        }

        if (!Objects.isNull(userDto.getPassword())) {
            user.setPassword(bcryptEncoder.encode(userDto.getPassword()));
        }

        if (!Objects.isNull(userDto.getCompanyId())) {
            user.setCompany(companyService.findOne(userDto.getCompanyId()));
        }

        return userRepository.save(user);
    }

    public long count() {
        return userRepository.count();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = false)
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Transactional(readOnly = false)
    public User authUser(Long id, String uuid) {
        User user = findOne(id);
        if (Objects.isNull(user)) {
            throw new UserException("User not exist!");
        }
        if (ObjectUtils.notEqual(user.getAuthUUID(), uuid)) {
            throw new UserException("Invalid UUID!");
        }
        user.setEnabled(true);
        userRepository.save(user);
        return user;
    }

    public void setAuthentication(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public User getCurrentUser() {
        Object userDetail = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (!(userDetail instanceof User)) {
            throw new UserException("Not authenticated!");
        }
        User user = (User) userDetail;
        return userRepository.findOne(user.getId());
    }

    public void clearAuthentication() {
        SecurityContextHolder.clearContext();
    }

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return bcryptEncoder.matches(rawPassword, encodedPassword);
    }

    public void sendAuthEmail(User user, String type) {
        StringBuilder sb = new StringBuilder();
        sb.append("링크를 클릭하면 인증이 완료됩니다!\n");
        sb.append("http://localhost:9700");
        sb.append("/users/");
        sb.append(user.getId());
        sb.append("/auth?type=");
        sb.append(type);
        sb.append("&uuid=");
        sb.append(user.getAuthUUID());

        MailMessage mailMessage = new MailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("[스탁블라인드] 인증 메일입니다.");
        mailMessage.setText(sb.toString());

        mailService.send(mailMessage);
    }
}
