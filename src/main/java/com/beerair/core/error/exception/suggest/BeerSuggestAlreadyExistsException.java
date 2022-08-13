package com.beerair.core.error.exception.suggest;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class BeerSuggestAlreadyExistsException extends BusinessException {
    public BeerSuggestAlreadyExistsException() {
        super(ErrorMessage.BEER_SUGGEST_ALREADY_EXISTS);
    }

    public BeerSuggestAlreadyExistsException(String reason) {
        super(ErrorMessage.BEER_SUGGEST_ALREADY_EXISTS, reason);
    }
}
