package com.beerair.core.unit.auth.presentation.filter;

import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.presentation.filter.AuthTokenAuthenticationFilter;
import com.beerair.core.auth.presentation.filter.SetAuthenticationStrategy;
import com.beerair.core.auth.presentation.tokenreader.AuthTokenReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

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
}
