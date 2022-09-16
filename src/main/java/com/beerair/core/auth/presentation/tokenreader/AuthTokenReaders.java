package com.beerair.core.auth.presentation.tokenreader;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequiredArgsConstructor
public class AuthTokenReaders implements AuthTokenReader {
    private final CookieAuthTokenReader cookieAuthTokenReader;
    private final HeaderAuthTokenReader headerAuthTokenReader;

    @Override
    public Optional<String> read(HttpServletRequest request) {
        var byHeader = headerAuthTokenReader.read(request);
        if (byHeader.isPresent()) {
            return byHeader;
        }
        return cookieAuthTokenReader.read(request);
    }
}
