package com.beerair.core.error.exception.common;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class MapperException extends BusinessException {
    public MapperException() {
        super(ErrorMessage.INTERNAL_SERVER_ERROR_BY_MAPPER);
    }

    public MapperException(String reason) {
        super(ErrorMessage.INTERNAL_SERVER_ERROR_BY_MAPPER, reason);
    }
}
