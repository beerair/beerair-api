package com.beerair.core.error.exception.member;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class MemberNotFoundException extends BusinessException {
    public MemberNotFoundException() {
        super(ErrorMessage.MEMBER_NOT_FOUND);
    }
}
