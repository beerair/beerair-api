package com.beerair.core.unit.auth.presentation;

import com.beerair.core.auth.application.AuthTokenService;
import com.beerair.core.auth.domain.AuthToken;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Member;
import com.beerair.core.auth.presentation.AuthTokenFailureHandler;
import com.beerair.core.auth.presentation.AuthTokenSuccessHandler;
import com.beerair.core.fixture.RedisTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthTokenSuccessHandlerTest {
    private static final String REDIRECT_EXPERT = "/expert";

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
    private OAuth2AuthenticationToken authentication;

    @BeforeEach
    void setUp() {
        authTokenSuccessHandler = AuthTokenSuccessHandler.builder()
                .successRedirectUri(REDIRECT_EXPERT)
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
        stubbingByIssueToken();
        stubbingAuthentication();
        RedisTestUtils.setDoNothing(redisTemplate);

        authTokenSuccessHandler.onAuthenticationSuccess(
                httpServletRequest, httpServletResponse, authentication
        );

        verify(httpServletResponse, times(1))
                .sendRedirect(REDIRECT_EXPERT);
        verify(httpServletResponse, times(2))
                .addCookie(any());
    }

    private void stubbingByIssueToken() {
        when(accessTokenCrypto.encrypt(any()))
                .thenReturn(new AuthToken("access", new Date()));
        when(refreshTokenCrypto.encrypt(any()))
                .thenReturn(new AuthToken("refresh", new Date()));
    }

    private void stubbingAuthentication() {
        var sample = new OAuth2Member(
                "", "", Collections.emptySet(), Collections.emptyMap()
        );
        when(authentication.getPrincipal())
                .thenReturn(sample);
    }
}
