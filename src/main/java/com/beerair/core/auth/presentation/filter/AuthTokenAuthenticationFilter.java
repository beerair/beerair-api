package com.beerair.core.auth.presentation.filter;

import com.beerair.core.auth.application.AuthTokenService;
import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.presentation.loginhandler.TokenDelivery;
import com.beerair.core.auth.presentation.tokenreader.AuthTokenReader;
import com.beerair.core.error.exception.BusinessException;
import com.beerair.core.error.exception.auth.ExpiredAuthTokenException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AuthTokenAuthenticationFilter extends OncePerRequestFilter {
    private final AuthTokenCrypto authTokenCrypto;
    private final AuthTokenReader authTokenReader;
    private final AuthTokenService refreshTokenService;
    private final TokenDelivery tokenDelivery;
    @Setter
    private SetAuthenticationStrategy setAuthenticationStrategy = new DefaultAuthenticationStrategy();

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            setAuthentication(request, response);
        } catch (BusinessException ignored) {
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    private void setAuthentication(HttpServletRequest request, HttpServletResponse response) {
        var access = authTokenReader.read(request);
        if (access.isEmpty()) {
            return;
        }
        AuthTokenAuthentication authentication;
        try {
            authentication = convert(access.get());
        } catch (ExpiredAuthTokenException e) {
            /* 쿠키 방식 일경우 재귀적으로 다시 인가할 수 있지만, 헤더 방식 일경우 재귀로 다시 인가하면 무한으로 재귀하게 됨 */
            var newAccess = refresh(request, response).orElseThrow(() -> e);
            authentication = convert(newAccess);
        }
        setAuthenticationStrategy.set(authentication);
    }

    private AuthTokenAuthentication convert(String token) {
        var authentication = authTokenCrypto.decrypt(token);
        authentication.setAuthenticated(true);
        return authentication;
    }

    @SneakyThrows
    private Optional<String> refresh(HttpServletRequest request, HttpServletResponse response) {
        var refreshToken = getRefreshToken(request);
        if (refreshToken.isPresent()) {
            var tokens = refreshTokenService.issueByRefreshToken(refreshToken.get());
            tokenDelivery.deliver(request, response, tokens.getAccessToken(), tokens.getRefreshToken());
            return Optional.of(
                    tokens.getAccessToken().getToken()
            );
        }
        return Optional.empty();
    }

    private Optional<String> getRefreshToken(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(eachCookie -> eachCookie.getName().equals("refreshToken"))
                .map(Cookie::getValue)
                .findFirst();
    }
}
