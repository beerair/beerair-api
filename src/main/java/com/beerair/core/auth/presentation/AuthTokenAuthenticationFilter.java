package com.beerair.core.auth.presentation;

import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.error.exception.BusinessException;
import com.beerair.core.error.exception.auth.TokenNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AuthTokenAuthenticationFilter extends OncePerRequestFilter {
    public static final String TOKEN_TYPE = "Bearer";
    private final AuthTokenCrypto accessTokenCrypto;
    @Setter
    private SetAuthenticationStrategy setAuthenticationStrategy = new DefaultSetAuthenticationStrategy();

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            getAccessToken(request)
                    .ifPresent(token -> {
                        var authentication = convert(token);
                        setAuthenticationStrategy.set(authentication);
                    });
        } catch (BusinessException ignored) {
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    private Optional<String> getAccessToken(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(eachCookie -> eachCookie.getName().equals("authorization"))
                .findFirst()
                .map(Cookie::getValue)
                .map(token -> {
                    if (token.startsWith(TOKEN_TYPE)) {
                        return token.split(" ")[1];
                    }
                    return null;
                });
    }

    private AuthTokenAuthentication convert(String token) {
        var authentication = accessTokenCrypto.decrypt(token);
        authentication.setAuthenticated(true);
        return authentication;
    }
}
