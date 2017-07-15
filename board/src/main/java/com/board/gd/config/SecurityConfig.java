package com.board.gd.config;

import com.board.gd.auth.EmailAuthenticationProvider;
import com.board.gd.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by gd.godong9 on 2017. 4. 6.
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailAuthenticationProvider userAuthenticationProvider;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(userAuthenticationProvider)
                .userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/companies/**").access("permitAll")
                .antMatchers("/stocks/**").access("permitAll")
                .antMatchers("/users/login", "/users/signup", "/users/email", "/users/*/auth", "/users/data").access("permitAll")
                .antMatchers("/users/password", "/users/find/password").access("permitAll")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/users/**").hasAuthority("USER")
                .antMatchers("/payments/**").hasAuthority("USER")
                .antMatchers("/boards/**").hasAuthority("PAID")
                .antMatchers("/posts/**").hasAuthority("PAID")
                .antMatchers("/comments/**").hasAuthority("PAID");
    }
}
