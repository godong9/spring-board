package com.board.gd.utils;

import com.board.gd.domain.user.User;
import com.board.gd.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by gd.godong9 on 2017. 4. 10.
 */

@Slf4j
public class SessionUtils {
    public static void setAuthentication(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public static User getUser() {
        Object userDetail = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (!(userDetail instanceof User)) {
            throw new UserException("Not authenticated!");
        }
        return (User) userDetail;
    }
}
