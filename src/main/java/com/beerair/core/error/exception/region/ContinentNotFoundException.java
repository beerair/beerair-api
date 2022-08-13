package com.beerair.core.error.exception.region;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class ContinentNotFoundException extends BusinessException {
    public ContinentNotFoundException() {
        super(ErrorMessage.CONTINENT_NOT_FOUND);
    }

    public ContinentNotFoundException(String reason) {
        super(ErrorMessage.CONTINENT_NOT_FOUND, reason);
    }
}
