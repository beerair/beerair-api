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
        // TODO :: 운영 환경에서는 보안 설정 추가 해야함
        var accessTokenCookie = toCookie("accessToken", accessToken);
        response.addCookie(accessTokenCookie);

        var refreshTokenCookie = toCookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        response.addCookie(refreshTokenCookie);
    }

    private Cookie toCookie(String cookieName, AuthToken authToken) {
        var cookie = new Cookie(cookieName, authToken.getToken());
        var maxAge = (int)(authToken.getExpired().getTime() - new Date().getTime()) / 1000;
        cookie.setMaxAge(maxAge);
        return cookie;
    }
}
