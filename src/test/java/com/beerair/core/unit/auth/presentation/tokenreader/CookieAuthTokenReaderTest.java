package com.beerair.core.unit.auth.presentation.tokenreader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.beerair.core.auth.presentation.tokenreader.CookieAuthTokenReader;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CookieAuthTokenReaderTest {
    private CookieAuthTokenReader reader;
    @Mock
    private HttpServletRequest httpServletRequest;

    @BeforeEach
    void setUp() {
        reader = new CookieAuthTokenReader();
    }

    @DisplayName("Cookie에서 Accesss Token을 가져온다.")
    @Test
    void read() {
        final String EXPERT = "1234";
        stubbingByGetCookies(EXPERT);

        var actual = reader.read(httpServletRequest);
        assertThat(actual.get()).isEqualTo(EXPERT);
    }

    private void stubbingByGetCookies(String expert) {
        var cookies = new Cookie[] {
                new Cookie("accessToken", expert)
        };
        when(httpServletRequest.getCookies())
                .thenReturn(cookies);
    }
}
