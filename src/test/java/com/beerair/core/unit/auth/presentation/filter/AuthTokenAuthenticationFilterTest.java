package com.beerair.core.unit.auth.presentation.filter;

import com.beerair.core.auth.application.AuthTokenService;
import com.beerair.core.auth.domain.AuthToken;
import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.dto.response.TokenRefreshResponse;
import com.beerair.core.auth.presentation.filter.AuthTokenAuthenticationFilter;
import com.beerair.core.auth.presentation.filter.SetAuthenticationStrategy;
import com.beerair.core.auth.presentation.loginhandler.CookieTokenDelivery;
import com.beerair.core.auth.presentation.tokenreader.AuthTokenReader;
import com.beerair.core.error.exception.auth.ExpiredAuthTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
    private AuthTokenReader authTokenReader;
    @Mock
    private AuthTokenService refreshTokenService;
    @Spy
    private CookieTokenDelivery tokenDelivery;

    @Mock
    private HttpServletRequest httpServletRequest;
    private MockHttpServletResponse httpServletResponse;
    @Mock
    private FilterChain filterChain;

    @Mock
    private AuthTokenAuthentication authentication;

    @BeforeEach
    void setUp() {
        httpServletResponse = new MockHttpServletResponse();
        authTokenAuthenticationFilter.setSetAuthenticationStrategy(setAuthenticationStrategy);
    }

    @DisplayName("request의 accessToken을 SetAuthenticationStrategy에 전달해 권한을 설정한다.")
    @Test
    void doFilterInternalFromAuthorizationCookie() throws ServletException, IOException {
        stubbingAuthTokenReader();
        stubbingCrypto();

        authTokenAuthenticationFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);

        verify(setAuthenticationStrategy, times(1))
                .set(any());
    }

    private void stubbingAuthTokenReader() {
        when(authTokenReader.read(httpServletRequest))
                .thenReturn(Optional.of("1234"));
    }

    private void stubbingCrypto() {
        when(accessTokenCrypto.decrypt(anyString()))
                .thenReturn(authentication);
    }

    @DisplayName("accessToken이 만료되었다면 cookie에 포함된 refreshToken으로 갱신한다.")
    @Test
    void refresh() throws ServletException, IOException {
        final String NEW_ACCESS = "NEW";
        final String NEW_REFRESH = "NEW_REFRESH";

        stubbingAuthTokenReader();
        stubbingCryptoByRefresh(NEW_ACCESS, NEW_REFRESH);

        authTokenAuthenticationFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);

        assertThat(httpServletResponse.getCookie("accessToken").getValue())
                .isEqualTo(NEW_ACCESS);
    }

    private void stubbingCryptoByRefresh(String newAccess, String newRefresh) {
        final String OLD_ACCESS = "OLD";
        final String OLD_REFRESH = "OLD_REFRESH";

        when(authTokenReader.read(httpServletRequest))
                .thenReturn(Optional.of(OLD_ACCESS));
        when(accessTokenCrypto.decrypt(OLD_ACCESS))
                .thenThrow(new ExpiredAuthTokenException());

        final Cookie[] COOKIE = new Cookie[] {
                new Cookie("refreshToken", OLD_REFRESH)
        };
        when(httpServletRequest.getCookies())
                .thenReturn(COOKIE);

        final TokenRefreshResponse TOKENS = new TokenRefreshResponse(
                new AuthToken(newAccess, new Date()), new AuthToken(newRefresh, new Date())
        );
        when(refreshTokenService.issueByRefreshToken(OLD_REFRESH))
                .thenReturn(TOKENS);
        when(accessTokenCrypto.decrypt(newAccess))
                .thenReturn(authentication);
    }
}
