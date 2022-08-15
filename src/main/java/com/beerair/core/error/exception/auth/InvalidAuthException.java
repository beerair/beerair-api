package com.beerair.core.error.exception.auth;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class InvalidAuthException extends BusinessException {
    public InvalidAuthException() {
        super(ErrorMessage.INVALID_AUTH);
    }
}
