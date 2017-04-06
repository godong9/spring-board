package com.board.gd.user;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @Autowired
    private HttpServletRequest request;

    /**
     * @api {post} /users/signup Request User signup
     * @apiName UserSignup
     * @apiGroup User
     *
     * @apiParam {String} name 이름
     * @apiParam {String} email 이메일
     * @apiParam {String} password 패스워드
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {String} [msg] 메시지
     * @apiSuccess {Object} user 유저 객체
     * @apiSuccess {String} user.id 유저 id
     * @apiSuccess {String} user.name 유저 이름
     */
    @PostMapping("/users/signup")
    public UserResult userSignup(@RequestBody @Valid UserForm userForm) {
        User user = userService.save(modelMapper.map(userForm, UserDto.class));
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);

        return UserResult.from(user, null);
    }

    @GetMapping("/users/me")
    public UserResult getUserMe() {
        User user = (User)request.getAttribute("user");

        return UserResult.from(user, null);
    }
}
