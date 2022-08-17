package com.beerair.core.error.exception.review;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class ReviewNotFoundException extends BusinessException {
    public ReviewNotFoundException() {
        super(ErrorMessage.REVIEW_NOT_FOUND);
    }

    public ReviewNotFoundException(String reason) {
        super(ErrorMessage.REVIEW_NOT_FOUND, reason);
    }
}
