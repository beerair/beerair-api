package com.beerair.core.auth.utils;

import lombok.experimental.UtilityClass;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@UtilityClass
public class CookieUtils {
    public static void deleteCookie(String key, HttpServletResponse response) {
        var cookie = new Cookie(key, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
