package com.beerair.core.auth.presentation;

import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.error.exception.BusinessException;
import com.beerair.core.error.exception.auth.TokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
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
    private final AuthTokenCrypto accessTokenCrypto;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            Optional<String> optionalToken = getToken(request);
            if (optionalToken.isEmpty()) {
                return;
            }
            String token = optionalToken.get();
            var authentication = convert(token);
            String memberId = authentication.getLoggedInUser().getId();

            verify(memberId, token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BusinessException ignored) {
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    public Optional<String> getToken(HttpServletRequest request) {
        String token = request.getHeader("authorization");
        if (Objects.isNull(token) || !token.startsWith(TOKEN_TYPE)) {
            return Optional.empty();
        }
        return Optional.of(token)
                .map(t -> t.split(" ")[1]);
    }

    private void verify(String memberId, String token) {
        Object raw = redisTemplate.opsForValue().get("authToken:" + memberId);
        var saved = Optional.ofNullable(raw).map(t -> (String) t);
        if (saved.isEmpty() || !token.equals(saved.get())) {
            throw new TokenNotFoundException();
        }
    }

    private AuthTokenAuthentication convert(String token) {
        AuthTokenAuthentication authentication = accessTokenCrypto.decrypt(token);
        authentication.setAuthenticated(true);
        return authentication;
    }
}
