package com.beerair.core.error.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 토큰이 없습니다."),
    REFRESH_TOKEN_ALREADY_USED(HttpStatus.CONFLICT, "사용할 수 없는 토큰 입니다."),
    NO_AUTH(HttpStatus.FORBIDDEN, "로그인이 필요합니다."),
    INVALID_AUTH(HttpStatus.FORBIDDEN, "올바르지 않은 로그인 입니다."),

    CONFLICT_ERROR(HttpStatus.BAD_REQUEST, "예기치 못한 에러가 발생했습니다."),

    INTERNAL_SERVER_ERROR_BY_MAPPER(HttpStatus.BAD_REQUEST, "예기치 못한 에러가 발생했습니다."),

    BEER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 존재하는 맥주입니다."),
    BEER_SUGGEST_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 제안한 맥주입니다."),
    BEER_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당하는 맥주 정보가 없습니다."),

    CONTINENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당하는 대륙 정보가 없습니다."),
    COUNTRY_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당하는 국가 정보가 없습니다."),

    REVIEW_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당하는 리뷰 정보가 없습니다."),
    ;

    private final HttpStatus status;
    private final String description;
}
