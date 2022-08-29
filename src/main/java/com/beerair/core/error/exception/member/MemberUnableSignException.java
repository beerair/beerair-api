package com.beerair.core.error.exception.member;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class MemberUnableSignException extends BusinessException {
    public MemberUnableSignException() {
        super(ErrorMessage.MEMBER_UNABLE_SIGN_BY_SIGNED);
    }

    public MemberUnableSignException(String reason) {
        super(ErrorMessage.MEMBER_UNABLE_SIGN_BY_SIGNED, reason);
    }
}
