package com.beerair.core.error.exception.suggest;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class BeerAlreadyExistsException extends BusinessException {
    public BeerAlreadyExistsException() {
        super(ErrorMessage.BEER_ALREADY_EXISTS);
    }

    public BeerAlreadyExistsException(String reason) {
        super(ErrorMessage.BEER_ALREADY_EXISTS, reason);
    }
}
