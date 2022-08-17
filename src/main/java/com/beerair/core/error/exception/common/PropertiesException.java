package com.beerair.core.error.exception.common;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class PropertiesException extends BusinessException {
    public PropertiesException(String reason) {
        super(ErrorMessage.INTERNAL_SERVER_ERROR_BY_PROPERTIES, reason);
    }
}
