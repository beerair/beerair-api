package com.beerair.core.auth.presentation.tokenreader;

import com.beerair.core.auth.domain.AuthToken;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class CookieAuthTokenReader implements AuthTokenReader {
    @Override
    public Optional<String> read(HttpServletRequest request) {
        var cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return Optional.empty();
        }
        return Arrays.stream(cookies)
                .filter(eachCookie -> eachCookie.getName().equals("accessToken"))
                .findFirst()
                .map(Cookie::getValue);
    }
}
