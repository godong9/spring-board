package com.board.gd.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gd.godong9 on 2017. 4. 3.
 */

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<GrantedAuthority> getRoles(Long userId) {
        return userRoleRepository.findByUserId(userId).stream()
                .map(UserRole::getRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public User save(UserDto userDto) {
        return userRepository.save(User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .profileImg(userDto.getProfileImg())
                .enabled(true)
                .build());
    }

    public long count() {
        return userRepository.count();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByEmail(username);
    }
}
