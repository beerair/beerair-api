package com.beerair.core.error.exception.auth;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class RefreshTokenNotFoundException extends BusinessException {
    public RefreshTokenNotFoundException() {
        super(ErrorMessage.REFRESH_TOKEN_NOT_FOUND);
    }
}
