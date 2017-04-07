package com.board.gd.authentication;

import com.board.gd.user.User;
import com.board.gd.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gd.godong9 on 2017. 4. 7.
 */

@Component
public class EmailAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String)authentication.getPrincipal();
        String password = (String)authentication.getCredentials();

        User user = userService.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Bad username");
        }
        if (!user.getPassword().equals(password)) {
            throw new BadCredentialsException("Bad credentials");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(email, password, roles);
        result.setDetails(user);
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
