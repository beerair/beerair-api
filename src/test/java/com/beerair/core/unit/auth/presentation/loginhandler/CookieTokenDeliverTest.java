package com.beerair.core.unit.auth.presentation.loginhandler;

import static org.assertj.core.api.Assertions.assertThat;

import com.beerair.core.auth.domain.AuthToken;
import com.beerair.core.auth.presentation.loginhandler.CookieTokenDelivery;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class CookieTokenDeliverTest {
    private CookieTokenDelivery cookieTokenDelivery;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        cookieTokenDelivery = new CookieTokenDelivery();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }


    @DisplayName("엑세스 토큰 쿠키 설정 값 확인")
    @Test
    void accessTokenCookieTest() throws ServletException, IOException {
        delver();

        var cookie = response.getCookie("accessToken");
        assertThat(cookie.isHttpOnly())
                .isFalse();
    }

    @DisplayName("리프레시 토큰 쿠키 설정 값 확인")
    @Test
    void refreshTokenCookieTest() throws ServletException, IOException {
        delver();

        var cookie = response.getCookie("refreshToken");
        assertThat(cookie.isHttpOnly())
                .isTrue();
    }

    private void delver() {
        cookieTokenDelivery.deliver(
                request,
                response,
                new AuthToken("123", new Date()),
                new AuthToken("456", new Date())
        );
    }
}
