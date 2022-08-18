package com.beerair.core.error.exception.auth;

import com.beerair.core.error.dto.ErrorMessage;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;

public class BadLoginRequestException extends OAuth2AuthenticationException {
    public BadLoginRequestException(String message) {
        super(new OAuth2Error(null, message, null));
    }

    public BadLoginRequestException() {
        this(ErrorMessage.BAD_LOGIN_REQUEST.getDescription());
    }
}
