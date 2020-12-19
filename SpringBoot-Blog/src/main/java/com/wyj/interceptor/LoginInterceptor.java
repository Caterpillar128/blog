package com.wyj.interceptor;

import com.wyj.constants.AllConstants;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("LoginInterceptor ...");
        if (request.getSession().getAttribute(AllConstants.USER_SESSION) == null){
            response.sendRedirect("/admin");
            // 不再往下执行
            return false;
        }
        // 继续往下执行
        return true;
    }
}
