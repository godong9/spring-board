package com.board.gd.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gd.godong9 on 2017. 4. 3.
 */

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Transactional
    public User save(UserDto userDto) {
        return userRepository.save(User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .fbId(userDto.getFbId())
                .password(userDto.getPassword())
                .profileImg(userDto.getProfileImg())
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
}
