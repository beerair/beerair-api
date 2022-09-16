package com.beerair.core.auth.presentation.tokenreader;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface AuthTokenReader {
    Optional<String> read(HttpServletRequest request);
}
