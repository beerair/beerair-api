package com.beerair.core.auth.presentation.loginhandler;

import com.beerair.core.auth.domain.AuthToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TokenDelivery {
    void deliver(HttpServletRequest request, HttpServletResponse response, AuthToken accessToken, AuthToken refreshToken);
}
