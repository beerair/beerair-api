package com.beerair.core.auth.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CookieUtils {
    public static void deleteCookie(String key, HttpServletResponse response) {
        var cookie = new Cookie(key, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
