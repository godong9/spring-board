package com.board.gd.user;

import com.board.gd.user.form.LoginForm;
import com.board.gd.user.form.SignupForm;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public UserResult userSignup(@RequestBody @Valid SignupForm signupForm) {
        User user = userService.save(modelMapper.map(signupForm, UserDto.class));
        return UserResult.from(user, null);
    }

    @PostMapping("/users/login")
    public UserResult userLogin(HttpSession session, @RequestBody @Valid LoginForm loginForm) {
        String email = loginForm.getEmail();
        String password = loginForm.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        return UserResult.from(HttpStatus.OK, "success");
    }

    @PostMapping("/users/logout")
    public void userLogout(HttpSession session) {
        session.invalidate();
    }

    @GetMapping("/users/me")
    public UserResult getUserMe() {
        Object userDetail = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (!(userDetail instanceof User)) {
            log.info("Not authenticated!");
            return null;
        }
        User user = (User) userDetail;
        return UserResult.from(user, null);
    }
}
