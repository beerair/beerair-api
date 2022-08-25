package com.beerair.core.error.exception.beer;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class BeerLikeNotFoundException extends BusinessException {
    public BeerLikeNotFoundException() {
        super(ErrorMessage.BEER_NOT_FOUND);
    }

    public BeerLikeNotFoundException(String reason) {
        super(ErrorMessage.BEER_NOT_FOUND, reason);
    }
}
