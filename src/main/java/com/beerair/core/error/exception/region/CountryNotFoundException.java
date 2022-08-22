package com.beerair.core.error.exception.region;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class CountryNotFoundException extends BusinessException {
    public CountryNotFoundException() {
        super(ErrorMessage.COUNTRY_NOT_FOUND);
    }

    public CountryNotFoundException(String reason) {
        super(ErrorMessage.COUNTRY_NOT_FOUND, reason);
    }
}
