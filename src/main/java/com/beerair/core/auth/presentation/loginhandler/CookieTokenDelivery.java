package com.beerair.core.auth.presentation.loginhandler;

import com.beerair.core.auth.domain.AuthToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class CookieTokenDelivery implements TokenDelivery {
    @Override
    public void deliver(HttpServletRequest request, HttpServletResponse response, AuthToken accessToken, AuthToken refreshToken) {
        int maxExpired = maxExpired(accessToken, refreshToken);

        // TODO :: 운영 환경에서는 보안 설정 추가 해야함 (https)
        var accessTokenCookie = toCookie("accessToken", accessToken, maxExpired);
        response.addCookie(accessTokenCookie);

        var refreshTokenCookie = toCookie("refreshToken", refreshToken, maxExpired);
        refreshTokenCookie.setHttpOnly(true);
        response.addCookie(refreshTokenCookie);
    }

    private int maxExpired(AuthToken accessToken, AuthToken refreshToken) {
        long max = Math.max(accessToken.getExpired().getTime(), refreshToken.getExpired().getTime());
        return (int)(max - new Date().getTime()) / 1000;
    }

    private Cookie toCookie(String cookieName, AuthToken authToken, int maxAge) {
        var cookie = new Cookie(cookieName, authToken.getToken());
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        return cookie;
    }
}
