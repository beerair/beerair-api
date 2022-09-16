package com.beerair.core.unit.auth.presentation.loginhandler;

import com.beerair.core.auth.application.RefreshTokenService;
import com.beerair.core.auth.domain.AuthToken;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Member;
import com.beerair.core.auth.presentation.loginhandler.AuthTokenFailureHandler;
import com.beerair.core.auth.presentation.loginhandler.AuthTokenSuccessHandler;
import com.beerair.core.auth.presentation.loginhandler.TokenDelivery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthTokenSuccessHandlerTest {
    private static final String REDIRECT_EXPERT = "/expert";

    private AuthTokenSuccessHandler authTokenSuccessHandler;
    @Mock
    private AuthTokenCrypto accessTokenCrypto;
    @Mock
    private AuthTokenCrypto refreshTokenCrypto;
    @Mock
    private RefreshTokenService refreshTokenService;
    @Mock
    private AuthTokenFailureHandler failureHandler;

    @Mock
    private HttpServletRequest httpServletRequest;
    private MockHttpServletResponse httpServletResponse;
    @Mock
    private OAuth2AuthenticationToken authentication;
    @Mock
    private TokenDelivery tokenDeliver;

    @BeforeEach
    void setUp() {
        httpServletResponse = new MockHttpServletResponse();
        authTokenSuccessHandler = AuthTokenSuccessHandler.builder()
                .successRedirectUri(REDIRECT_EXPERT)
                .accessTokenCrypto(accessTokenCrypto)
                .refreshTokenCrypto(refreshTokenCrypto)
                .refreshTokenService(refreshTokenService)
                .failureHandler(failureHandler)
                .tokenDeliver(tokenDeliver)
                .build();
    }

    @DisplayName("로그인 성공 후 리다이렉트 한다.")
    @Test
    void handle() throws ServletException, IOException {
        onAuthenticationSuccess();

        assertThat(httpServletResponse.getRedirectedUrl())
                .isEqualTo(REDIRECT_EXPERT);
    }

    @DisplayName("로그인 성공 후 토큰을 프론트에 전달한다.")
    @Test
    void deliver() throws ServletException, IOException {
        onAuthenticationSuccess();

        verify(tokenDeliver, times(1))
                .deliver(
                        any(HttpServletRequest.class),
                        any(HttpServletResponse.class),
                        any(AuthToken.class),
                        any(AuthToken.class)
                );
    }

    private void onAuthenticationSuccess() throws ServletException, IOException {
        stubbingTokenCrypto();
        stubbingAuthentication();

        authTokenSuccessHandler.onAuthenticationSuccess(
                httpServletRequest, httpServletResponse, authentication
        );
    }

    private void stubbingTokenCrypto() {
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
