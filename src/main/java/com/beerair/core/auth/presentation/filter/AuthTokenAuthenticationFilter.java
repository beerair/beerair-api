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
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
            authTokenReader.read(request)
                    .ifPresent(token -> {
                        AuthTokenAuthentication authentication;
                        try {
                            authentication = convert(token);
                        } catch (ExpiredAuthTokenException ignored) {
                            authentication = convert(refresh(request, response));
                        }
                        setAuthenticationStrategy.set(authentication);
                    });
        } catch (BusinessException ignored) {
        } finally {
            if (!(filterChain instanceof NullFilterChain)) {
                filterChain.doFilter(request, response);
            }
        }
    }

    private AuthTokenAuthentication convert(String token) {
        var authentication = authTokenCrypto.decrypt(token);
        authentication.setAuthenticated(true);
        return authentication;
    }

    @SneakyThrows
    private String refresh(HttpServletRequest request, HttpServletResponse response) {
        var refreshToken = getRefreshToken(request);
        if (refreshToken.isPresent()) {
            var tokens = refreshTokenService.issueByRefreshToken(refreshToken.get());
            tokenDelivery.deliver(request, response, tokens.getAccessToken(), tokens.getRefreshToken());
            return tokens.getAccessToken().getToken();
        }
        return null;
    }

    private Optional<String> getRefreshToken(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(eachCookie -> eachCookie.getName().equals("refreshToken"))
                .map(Cookie::getValue)
                .findFirst();
    }

    private static class NullFilterChain implements FilterChain {
        private static final NullFilterChain INSTANCE = new NullFilterChain();

        @Override
        public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {

        }
    }
}
