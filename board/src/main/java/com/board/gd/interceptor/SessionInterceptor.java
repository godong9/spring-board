package com.board.gd.interceptor;

import com.board.gd.user.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by gd.godong9 on 2017. 4. 6.
 */

public class SessionInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/error");
            return false;
        }
        User user = (User)session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/error");
            return false;
        }
        request.setAttribute("user", user);

        return true;
    }
}
