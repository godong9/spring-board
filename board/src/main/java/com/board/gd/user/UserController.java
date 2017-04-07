package com.board.gd.user;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    private AuthenticationManager authenticationManager;


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
    public UserResult userSignup(@RequestBody @Valid SignupForm userForm) {
        User user = userService.save(modelMapper.map(userForm, UserDto.class));
//        HttpSession session = request.getSession(true);
//        session.setAttribute("user", user);

        return UserResult.from(user, null);
    }

    @PostMapping("/users/login")
    public void userLogin(HttpSession session, @RequestBody @Valid LoginForm loginForm) {
//        HttpSession session = request.getSession(true);
//        session.setAttribute("user", user);
        String email = loginForm.getEmail();
        String password = loginForm.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(token);
        log.info("======== LOGIN ========");

        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());
    }

    @PostMapping("/users/logout")
    public void userLogout(HttpSession session) {
        session.invalidate();
    }

    @GetMapping("/users/me")
    public void getUserMe(HttpSession session) {

        Object userDetail = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (!(userDetail instanceof User)) {
            return;
        }

        User user = (User) userDetail;
//                User user = (User)request.getAttribute("user");
        log.info("USER: {}", user.getEmail());
//        return UserResult.from(user, null);
    }
}
