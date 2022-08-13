package com.beerair.core.error.exception.beer;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class BeerNotFoundException extends BusinessException {
    public BeerNotFoundException() {
        super(ErrorMessage.BEER_NOT_FOUND);
    }

    public BeerNotFoundException(String reason) {
        super(ErrorMessage.BEER_NOT_FOUND, reason);
    }
}
