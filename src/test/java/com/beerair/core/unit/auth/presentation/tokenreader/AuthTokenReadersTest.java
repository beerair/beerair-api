package com.beerair.core.unit.auth.presentation.tokenreader;

import com.beerair.core.auth.presentation.tokenreader.AuthTokenReaders;
import com.beerair.core.auth.presentation.tokenreader.CookieAuthTokenReader;
import com.beerair.core.auth.presentation.tokenreader.HeaderAuthTokenReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthTokenReadersTest {
    @InjectMocks
    private AuthTokenReaders authTokenReaders;
    @Mock
    private CookieAuthTokenReader cookieAuthTokenReader;
    @Mock
    private HeaderAuthTokenReader headerAuthTokenReader;

    private MockHttpServletRequest request;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
    }

    @DisplayName("쿠키에 토큰이 있다면 가져온다. (쿠키에서 먼저 가져온다.)")
    @Test
    void readByCookie() {
        when(cookieAuthTokenReader.read(request))
                .thenReturn(Optional.of("aaa"));

        var actual = authTokenReaders.read(request);
        assertThat(actual).isNotNull();
    }

    @DisplayName("헤더에 토큰이 있다면 가져온다.")
    @Test
    void readByHeader() {
        when(headerAuthTokenReader.read(request))
                .thenReturn(Optional.of("aaa"));

        var actual = headerAuthTokenReader.read(request);
        assertThat(actual).isNotNull();
    }
}
