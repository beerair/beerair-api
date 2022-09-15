package com.beerair.core.unit.auth.presentation;

import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Member;
import com.beerair.core.auth.presentation.AuthTokenAuthenticationFilter;
import com.beerair.core.auth.presentation.SetAuthenticationStrategy;
import com.beerair.core.fixture.RedisTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static com.beerair.core.auth.presentation.AuthTokenAuthenticationFilter.TOKEN_TYPE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AuthTokenAuthenticationFilterTest {
    @InjectMocks
    private AuthTokenAuthenticationFilter authTokenAuthenticationFilter;
    @Mock
    private AuthTokenCrypto accessTokenCrypto;
    @Mock
    private SetAuthenticationStrategy setAuthenticationStrategy;

    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private FilterChain filterChain;

    @Mock
    private AuthTokenAuthentication authentication;

    @BeforeEach
    void setUp() {
        authTokenAuthenticationFilter.setSetAuthenticationStrategy(setAuthenticationStrategy);
    }

    @DisplayName("authorization cookie를 가져와 엑세스 토큰으로 사용한다.")
    @Test
    void doFilterInternalFromAuthorizationCookie() throws ServletException, IOException {
        stubbingByGetCookies();
        stubbingCrypto();

        authTokenAuthenticationFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);

        verify(setAuthenticationStrategy, times(1))
                .set(any());
    }

    private void stubbingByGetCookies() {
        var sample = new Cookie[] {
                new Cookie("authorization", TOKEN_TYPE + " 1234")
        };
        when(httpServletRequest.getCookies())
                .thenReturn(sample);
    }

    private void stubbingCrypto() {
        when(accessTokenCrypto.decrypt(anyString()))
                .thenReturn(authentication);
    }
}
