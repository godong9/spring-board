package com.board.gd.domain.user;

import com.board.gd.exception.UserException;
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

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by gd.godong9 on 2017. 4. 3.
 */

@Transactional(readOnly = true)
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

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
                .map(userRole -> userRole.getRole().name())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = false)
    public UserRole createUserRole(User user, UserRoleType userRole) {
        if (Objects.isNull(user)) {
            throw new UserException("Fail createUserRole!");
        }
        return userRoleRepository.save(UserRole.builder()
                .role(userRole)
                .user(user)
                .build());
    }

    @Transactional(readOnly = false)
    public User create(UserDto userDto) {
        User user = userRepository.save(User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(bcryptEncoder.encode(userDto.getPassword()))
                .profileImg(userDto.getProfileImg())
                .enabled(true)
                .build());

        createUserRole(user, UserRoleType.USER);

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
}
