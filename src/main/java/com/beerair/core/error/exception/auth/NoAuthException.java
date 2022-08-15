package com.beerair.core.error.exception.auth;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class NoAuthException extends BusinessException {
    public NoAuthException() {
        super(ErrorMessage.NO_AUTH);
    }
}
