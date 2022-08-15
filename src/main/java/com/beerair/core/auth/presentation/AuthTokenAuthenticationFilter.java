package com.beerair.core.auth.presentation;

import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AuthTokenAuthenticationFilter extends OncePerRequestFilter {
    public static final String TOKEN_TYPE = "Bearer";
    private final AuthTokenProvider authTokenProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        Optional<String> token = getToken(request);

        token.map(this::convert)
                .ifPresent(SecurityContextHolder.getContext()::setAuthentication);
        filterChain.doFilter(request, response);
    }

    private Optional<String> getToken(HttpServletRequest request) {
        String token = request.getHeader("authorization");
        if (Objects.isNull(token) || !token.startsWith(TOKEN_TYPE)) {
            return Optional.empty();
        }
        return Optional.of(token)
                .map(t -> t.split(" ")[1]);
    }

    private Authentication convert(String token) {
        AuthTokenAuthentication authentication = AuthTokenAuthentication
                .builder()
                .token(token)
                .memberId(authTokenProvider.getId(token))
                .authorities(authTokenProvider.getAuthorities(token))
                .build();
        authentication.setAuthenticated(true);
        return authentication;
    }
}
