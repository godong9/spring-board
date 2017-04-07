package com.board.gd.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gd.godong9 on 2017. 4. 3.
 */

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

//    public ArrayList<GrantedAuthority> getRoles(String email) {
//
//    }

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
