package com.beerair.core.error.exception.auth;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class InvalidAuthTokenException extends BusinessException {
    public InvalidAuthTokenException() {
        super(ErrorMessage.INVALID_AUTH_TOKEN);
    }
}
