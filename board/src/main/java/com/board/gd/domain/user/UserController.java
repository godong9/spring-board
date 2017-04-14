package com.board.gd.domain.user;

import com.board.gd.domain.user.form.LoginForm;
import com.board.gd.domain.user.form.SignupForm;
import com.board.gd.domain.user.form.UpdateForm;
import com.board.gd.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    private AuthenticationManager authenticationManager;

    @Value("${spring.session.key}")
    private String sessionKey;

    /**
     * @api {post} /users/signup Request User signup
     * @apiName SignupUser
     * @apiGroup User
     *
     * @apiParam {String} name 이름
     * @apiParam {String} email 이메일
     * @apiParam {String} password 패스워드
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {String} [message] 메시지
     * @apiSuccess {Object} data 유저 객체
     * @apiSuccess {Number} data.id 유저 id
     * @apiSuccess {String} data.name 유저 이름
     *
     * @apiUse BadRequestError
     */
    @PostMapping("/users/signup")
    public ServerResponse userSignup(@RequestBody @Valid SignupForm signupForm) {
        User user = userService.create(modelMapper.map(signupForm, UserDto.class));
        return ServerResponse.success(modelMapper.map(user, UserResult.class));
    }

    /**
     * @api {post} /users/login Request User login
     * @apiName LoginUser
     * @apiGroup User
     *
     * @apiParam {String} email 이메일
     * @apiParam {String} password 패스워드
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {String} [message] 메시지
     *
     * @apiUse BadRequestError
     */
    @PostMapping("/users/login")
    public ServerResponse userLogin(@RequestBody @Valid LoginForm loginForm) {
        String email = loginForm.getEmail();
        String password = loginForm.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(token);

        userService.setAuthentication(authentication);

        return ServerResponse.success();
    }

    /**
     * @api {post} /users/logout Request User logout
     * @apiName LogoutUser
     * @apiGroup User
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {String} [message] 메시지
     */
    @PostMapping("/users/logout")
    public ServerResponse userLogout() {
        userService.clearAuthentication();
        return ServerResponse.success();
    }

    /**
     * @api {get} /users/me Request User me data
     * @apiName GetMeUser
     * @apiGroup User
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {String} [message] 메시지
     * @apiSuccess {Object} data 유저 객체
     * @apiSuccess {Number} data.id 유저 id
     * @apiSuccess {String} data.name 유저 이름
     *
     * @apiUse BadRequestError
     */
    @GetMapping("/users/me")
    public ServerResponse getUserMe() {
        return ServerResponse.success(userService.getCurrentUser());
    }

    /**
     * @api {put} /users Request User update
     * @apiName UpdateUser
     * @apiGroup User
     *
     * @apiParam {String} [name] 유저 이름
     * @apiParam {String} [password] 패스워드
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {String} [message] 메시지
     * @apiSuccess {Object} data 유저 객체
     * @apiSuccess {Number} data.id 유저 id
     * @apiSuccess {String} data.name 유저 이름
     *
     * @apiUse BadRequestError
     */
    @PutMapping("/users")
    public ServerResponse PutUser(@RequestBody @Valid UpdateForm updateForm) {
        updateForm.setId(userService.getCurrentUser().getId());
        User user = userService.update(modelMapper.map(updateForm, UserDto.class));
        return ServerResponse.success(user);
    }

}
