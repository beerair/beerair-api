package com.beerair.core.error.exception.auth;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class BadLoginRequestException extends BusinessException {
    public BadLoginRequestException() {
        super(ErrorMessage.BAD_LOGIN_REQUEST);
    }
}
