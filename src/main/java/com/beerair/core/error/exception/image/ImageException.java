package com.beerair.core.error.exception.image;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class ImageException extends BusinessException {
    public ImageException() {
        super(ErrorMessage.IMAGE_SERVER_ERROR);
    }
}
