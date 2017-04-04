package com.board.gd.user;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by gd.godong9 on 2017. 4. 3.
 */

@Slf4j
@RestController
public class UserController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @PostMapping("/users/signup")
    public UserResult userSignup(@RequestBody @Valid UserForm userForm) {
        User user = userService.save(modelMapper.map(userForm, UserDto.class));
        return UserResult.from(user, "success");
    }
}
