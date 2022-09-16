package com.beerair.core.auth.presentation.aop;

import com.beerair.core.auth.utils.CookieUtils;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReSignUpInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)  {
        if (!request.getMethod().equals(HttpMethod.DELETE.name())) {
            return;
        }
        CookieUtils.deleteCookie("accessToken", response);
        CookieUtils.deleteCookie("refreshToken", response);
    }
}
