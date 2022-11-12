package com.beerair.core.error.exception.common;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class InvalidRequestParameterException extends BusinessException {
    public InvalidRequestParameterException(String reason) {
        super(ErrorMessage.INVALID_REQUEST_PARMAETER, reason);
    }
}
