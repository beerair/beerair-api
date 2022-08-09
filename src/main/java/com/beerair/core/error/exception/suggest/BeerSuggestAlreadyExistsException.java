package com.beerair.core.error.exception.suggest;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class BeerSuggestAlreadyExistsException extends BusinessException {
    public BeerSuggestAlreadyExistsException(ErrorMessage message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
