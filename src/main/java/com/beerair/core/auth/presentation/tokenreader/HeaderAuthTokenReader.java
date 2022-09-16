package com.beerair.core.auth.presentation.tokenreader;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

public class HeaderAuthTokenReader implements AuthTokenReader {
    public static final String TOKEN_TYPE = "Bearer";

    @Override
    public Optional<String> read(HttpServletRequest request) {
        var token = request.getHeader("authorization");
        if (Objects.isNull(token) || !token.startsWith(TOKEN_TYPE)) {
            return Optional.empty();
        }
        return Optional.of(token)
                .map(t -> t.split(" ")[1]);
    }
}
