package com.beerair.core.error.exception.auth;

import com.beerair.core.error.dto.ErrorMessage;
import org.springframework.security.core.AuthenticationException;

public class BadLoginRequestException extends AuthenticationException {
    public BadLoginRequestException(String message) {
        super(message);
    }

    public BadLoginRequestException() {
        this(ErrorMessage.BAD_LOGIN_REQUEST.getDescription());
    }
}
