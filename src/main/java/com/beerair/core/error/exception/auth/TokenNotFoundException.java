package com.beerair.core.error.exception.auth;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class TokenNotFoundException extends BusinessException {
    public TokenNotFoundException() {
        super(ErrorMessage.AUTH_TOKEN_NOT_FOUND);
    }
}
