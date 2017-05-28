package com.board.gd.domain.user;

import com.board.gd.domain.user.form.*;
import com.board.gd.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
     * @api {post} /users/email Request User email
     * @apiName AuthUserEmail
     * @apiGroup User
     *
     * @apiParam {String} email 이메일
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Object} data 유저 객체
     * @apiSuccess {Number} data.id 유저 id
     * @apiSuccess {String} data.name 유저 이름
     *
     * @apiUse BadRequestError
     */
    @PostMapping("/users/email")
    public ServerResponse postUserEmail(@RequestBody @Valid EmailForm emailForm) {
        userService.createUserEmail(modelMapper.map(emailForm, UserDto.class));
        return ServerResponse.success();
    }

    /**
     * @api {post} /users/find/password Request User find password
     * @apiName FindUserPassword
     * @apiGroup User
     *
     * @apiParam {String} email 이메일
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Object} data 유저 객체
     * @apiSuccess {Number} data.id 유저 id
     * @apiSuccess {String} data.name 유저 이름
     *
     * @apiUse BadRequestError
     */
    @PostMapping("/users/find/password")
    public ServerResponse postUserFindPassword(@RequestBody @Valid EmailForm emailForm) {
        userService.updateAuthInfo(modelMapper.map(emailForm, UserDto.class));
        return ServerResponse.success();
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
     *
     * @apiSuccessExample {json} Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *       "status":200,
     *       "data":null,
     *       "count":null,
     *       "error":null
     *     }
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
     * @apiSuccess {Object} data 유저 객체
     * @apiSuccess {Number} data.id 유저 id
     * @apiSuccess {String} data.name 유저 이름
     * @apiSuccess {String} data.email 유저 이메일
     *
     * @apiUse BadRequestError
     */
    @GetMapping("/users/me")
    public ServerResponse getUserMe() {
        User user = userService.getCurrentUser();
        return ServerResponse.success(modelMapper.map(user, UserResult.class));
    }

    /**
     * @api {get} /users/:id/auth Request User auth
     * @apiName GetAuthUser
     * @apiGroup User
     *
     * @apiParam {String} type 이동할 페이지 구분 (auth: 인증, password: 패스워드 초기화)
     * @apiParam {String} uuid 인증을 위한 UUID
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Object} data 유저 객체
     * @apiSuccess {Number} data.id 유저 id
     * @apiSuccess {String} data.name 유저 이름
     *
     * @apiSampleRequest http://localhost:9700/users/111/auth?type=auth&uuid=3051a8d7-aea7-1801-e0bf-bc539dd60cf3
     *
     * @apiUse BadRequestError
     */
    @GetMapping("/users/{id}/auth")
    public RedirectView getUserAuth(@PathVariable @Valid Long id, @RequestParam("type") String type, @RequestParam("uuid") String uuid) {
        RedirectView redirectView = new RedirectView();
        User user = userService.authUser(id, uuid);
        StringBuilder sb = new StringBuilder();
        sb.append("http://localhost:8080/#");
        switch(type) {
            case "auth":
                sb.append("/user-update?id=");
                sb.append(user.getId());
                sb.append("&email=");
                sb.append(user.getEmail());
                sb.append("&uuid=");
                sb.append(uuid);
                break;
            case "password":
                sb.append("reset-password?uuid=");
                sb.append(uuid);
                break;
            default:
                sb.append("error");
                break;
        }
        redirectView.setUrl(sb.toString());
        return redirectView;
    }

    /**
     * @api {put} /users Request User update
     * @apiName UpdateUser
     * @apiGroup User
     *
     * @apiParam {String} [name] 유저 이름
     * @apiParam {String} [password] 패스워드
     * @apiParam {Number} [company_id] 회사 ID
     *
     * @apiSuccess {Number} status 상태코드
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
        return ServerResponse.success(modelMapper.map(user, UserResult.class));
    }

    /**
     * @api {put} /users/data Request User data update
     * @apiName UpdateUserData
     * @apiGroup User
     *
     * @apiParam {Number} id 유저 id
     * @apiParam {String} uuid 인증을 위한 uuid
     * @apiParam {String} name 유저 이름
     * @apiParam {String} password 패스워드
     * @apiParam {Number} company_id 회사 ID
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Object} data 유저 객체
     * @apiSuccess {Number} data.id 유저 id
     * @apiSuccess {String} data.name 유저 이름
     *
     * @apiUse BadRequestError
     */
    @PutMapping("/users/data")
    public ServerResponse PutUserData(@RequestBody @Valid UpdateDataForm updateDataForm) {
        User user = userService.updateUserData(modelMapper.map(updateDataForm, UserDto.class));
        return ServerResponse.success(modelMapper.map(user, UserResult.class));
    }
}
