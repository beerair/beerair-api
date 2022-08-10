package com.beerair.core.error.exception.suggest;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class BeerAlreadyExistsException extends BusinessException {
    public BeerAlreadyExistsException(ErrorMessage message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
