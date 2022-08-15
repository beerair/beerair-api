package com.beerair.core.error.exception.auth;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class RefreshTokenAlreadyUsedException extends BusinessException {
    public RefreshTokenAlreadyUsedException() {
        super(ErrorMessage.REFRESH_TOKEN_ALREADY_USED);
    }
}
