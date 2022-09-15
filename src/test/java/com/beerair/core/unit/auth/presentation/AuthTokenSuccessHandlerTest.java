package com.beerair.core.unit.auth.presentation;

import com.beerair.core.auth.application.AuthTokenService;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.presentation.AuthTokenFailureHandler;
import com.beerair.core.auth.presentation.AuthTokenSuccessHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthTokenSuccessHandlerTest {
    private static final String REDIRECT_URI = "REDIRECT_URI";

    private AuthTokenSuccessHandler authTokenSuccessHandler;
    @Mock
    private RedisTemplate<String, Object> redisTemplate;
    @Mock
    private AuthTokenCrypto accessTokenCrypto;
    @Mock
    private AuthTokenCrypto refreshTokenCrypto;
    @Mock
    private AuthTokenService refreshTokenService;
    @Mock
    private AuthTokenFailureHandler failureHandler;

    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        authTokenSuccessHandler = AuthTokenSuccessHandler.builder()
                .successRedirectUri(REDIRECT_URI)
                .redisTemplate(redisTemplate)
                .accessTokenCrypto(accessTokenCrypto)
                .refreshTokenCrypto(refreshTokenCrypto)
                .refreshTokenService(refreshTokenService)
                .failureHandler(failureHandler)
                .build();
    }

    @DisplayName("쿠키에 토큰값을 담아주고, 리다이렉트 한다.")
    @Test
    void handle() throws ServletException, IOException {
        authTokenSuccessHandler.onAuthenticationSuccess(
                httpServletRequest, httpServletResponse, authentication
        );

        verify(httpServletResponse, times(1))
                .sendRedirect(REDIRECT_URI);
        verify(httpServletResponse, times(1))
                .addCookie(any());
    }
}
