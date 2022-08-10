package com.beerair.core.error.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    CONFLICT_ERROR("예기치 못한 에러가 발생했습니다."),

    INTERNAL_SERVER_ERROR_BY_MAPPER("예기치 못한 에러가 발생했습니다."),

    BEER_ALREADY_EXISTS_EXCEPTION("이미 존재하는 맥주입니다."),
    BEER_SUGGEST_ALREADY_EXISTS_EXCEPTION("이미 제안한 맥주입니다."),

    ;

    private final String description;
}
