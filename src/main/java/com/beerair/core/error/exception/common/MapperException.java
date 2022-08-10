package com.beerair.core.error.exception.common;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class MapperException extends BusinessException {
    public MapperException(ErrorMessage message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
