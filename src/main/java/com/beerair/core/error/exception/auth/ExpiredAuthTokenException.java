package com.beerair.core.error.exception.auth;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class ExpiredAuthTokenException extends BusinessException {
    public ExpiredAuthTokenException() {
        super(ErrorMessage.EXPIRED_AUTH_TOKEN);
    }
}
