package com.board.gd.domain.user;

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
    public User create(UserDto userDto) {
        User user = userRepository.save(User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(bcryptEncoder.encode(userDto.getPassword()))
                .profileImg(userDto.getProfileImg())
                .authUUID(UUID.randomUUID().toString())
                .enabled(false)
                .build());

        sendSignupEmail(user);

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
    public void authUser(Long id, String uuid) {
        User user = findOne(id);
        if (Objects.isNull(user)) {
            throw new UserException("User not exist!");
        }
        if (ObjectUtils.notEqual(user.getAuthUUID(), uuid)) {
            throw new UserException("Invalid UUID!");
        }
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void setAuthentication(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public User getCurrentUser() {
        Object userDetail = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (!(userDetail instanceof User)) {
            throw new UserException("Not authenticated!");
        }
        return (User) userDetail;
    }

    public void clearAuthentication() {
        SecurityContextHolder.clearContext();
    }

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return bcryptEncoder.matches(rawPassword, encodedPassword);
    }

    public void sendSignupEmail(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("링크를 클릭하면 인증이 완료됩니다!\n");
        sb.append("http://www.stockblind.kr/users/");
        sb.append(user.getId());
        sb.append("/auth?uuid=");
        sb.append(user.getAuthUUID());

        MailMessage mailMessage = new MailMessage();
        mailMessage.setFrom("stockblind.kr@gmail.com");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("[스탁블라인드] 인증 메일입니다.");
        mailMessage.setHtmlContent(true);
        mailMessage.setText(sb.toString());
        mailMessage.setEncoding("UTF-8");

        mailService.send(mailMessage);
    }
}
