package com.beerair.core.auth.presentation.tokenreader;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

public interface AuthTokenReader {
    Optional<String> read(HttpServletRequest request);
}
