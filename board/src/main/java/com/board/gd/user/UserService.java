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

    public UserDao findOne(Integer id) {
        return userRepository.findOne(id);
    }

    @Transactional
    public UserDao save(UserDto userDto) {
        UserDao userDao = UserDao.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .fbId(userDto.getFbId())
                .password(userDto.getPassword())
                .profileImg(userDto.getProfileImg())
                .build();

        return userRepository.save(userDao);
    }

    public List<UserDao> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
