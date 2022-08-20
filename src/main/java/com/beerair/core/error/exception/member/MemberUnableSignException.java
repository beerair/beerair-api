package com.beerair.core.error.exception.member;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class MemberUnableSignException extends BusinessException {
    public MemberUnableSignException(ErrorMessage message) {
        super(message);
    }
}
