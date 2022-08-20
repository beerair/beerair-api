package com.beerair.core.error.exception.member;

import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;

public class MemberNicknameAlreadyExistsException extends BusinessException {
    public MemberNicknameAlreadyExistsException() {
        super(ErrorMessage.MEMBER_NICKNAME_ALREADY_EXISTS);
    }
}
