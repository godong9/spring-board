package com.board.gd.configuration;

import com.board.gd.authentication.EmailAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by gd.godong9 on 2017. 4. 6.
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private EmailAuthenticationProvider userAuthenticationProvider;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userAuthenticationProvider);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/users/login", "/users/signup")
                    .access("permitAll");
    }

}
