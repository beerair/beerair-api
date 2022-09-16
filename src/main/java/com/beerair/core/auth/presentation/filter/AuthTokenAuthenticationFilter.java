package com.beerair.core.auth.presentation.filter;

import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.presentation.tokenreader.AuthTokenReader;
import com.beerair.core.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class AuthTokenAuthenticationFilter extends OncePerRequestFilter {
    private final AuthTokenCrypto authTokenCrypto;
    private final AuthTokenReader authTokenReader;
    @Setter
    private SetAuthenticationStrategy setAuthenticationStrategy = new DefaultSetAuthenticationStrategy();

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            authTokenReader.read(request)
                    .ifPresent(token -> {
                        var authentication = convert(token);
                        setAuthenticationStrategy.set(authentication);
                    });
        } catch (BusinessException ignored) {
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    private AuthTokenAuthentication convert(String token) {
        var authentication = authTokenCrypto.decrypt(token);
        authentication.setAuthenticated(true);
        return authentication;
    }
}
