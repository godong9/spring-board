package com.board.gd.auth;

import com.board.gd.domain.user.User;
import com.board.gd.domain.user.UserService;
import com.board.gd.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Created by gd.godong9 on 2017. 4. 7.
 */

@Component
public class EmailAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String)authentication.getPrincipal();
        String password = (String)authentication.getCredentials();

        User user = userService.findByEmail(email);

        if (Objects.isNull(user)) {
            throw new UserException("존재하지 않는 유저입니다.");
        }

        if (!userService.matchPassword(password, user.getPassword())) {
            throw new UserException("비밀번호가 잘못되었습니다.");
        }

        if (!user.getEnabled()) {
            userService.sendAuthEmail(user, "auth");
            throw new UserException("인증되지 않은 유저입니다. 인증 메일을 확인해주세요.");
        }

        List<GrantedAuthority> roles = userService.findRolesByUserId(user.getId());
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(email, password, roles);
        result.setDetails(user);
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
