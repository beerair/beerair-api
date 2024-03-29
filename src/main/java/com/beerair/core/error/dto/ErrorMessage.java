package com.beerair.core.error.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    /** Server Error Message */
    CONFLICT_ERROR(HttpStatus.BAD_REQUEST, "예기치 못한 에러가 발생했습니다."),
    INTERNAL_SERVER_ERROR_BY_MAPPER(HttpStatus.BAD_REQUEST, "예기치 못한 에러가 발생했습니다."),
    INTERNAL_SERVER_ERROR_BY_PROPERTIES(HttpStatus.INTERNAL_SERVER_ERROR, "예기치 못한 에러가 발생했습니다."),
    INVALID_REQUEST_PARMAETER(HttpStatus.BAD_REQUEST, "잘못된 요청 입니다."),

    /** Auth Error Message */
    INVALID_AUTH_TOKEN(HttpStatus.FORBIDDEN, "유효하지 않은 인증 토큰 입니다."),
    EXPIRED_AUTH_TOKEN(HttpStatus.FORBIDDEN, "만료되어 사용할 수 없는 인증 토큰 입니다."),
    AUTH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 토큰이 없습니다."),
    NO_AUTH(HttpStatus.FORBIDDEN, "로그인이 필요합니다."),
    BAD_LOGIN_REQUEST(HttpStatus.BAD_REQUEST, "올바르지 않은 로그인 입니다."),

    /** Member Error Message */
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 유저 정보가 없습니다."),
    MEMBER_UNABLE_SIGN_BY_SIGNED(HttpStatus.CONFLICT, "이미 회원가입이 완료된 유저 입니다."),
    MEMBER_NICKNAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 닉네임 입니다."),

    /** Beer Error Message */
    BEER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 존재하는 맥주입니다."),
    BEER_SUGGEST_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 제안한 맥주입니다."),
    BEER_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당하는 맥주 정보가 없습니다."),
    BEER_LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "좋아요한 맥주 정보가 없습니다."),

    /** Region Error Message */
    CONTINENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당하는 대륙 정보가 없습니다."),
    COUNTRY_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당하는 국가 정보가 없습니다."),

    /** Review Error Message */
    REVIEW_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당하는 리뷰 정보가 없습니다."),

    /** Image Error Message */
    IMAGE_SERVER_ERROR(HttpStatus.BAD_REQUEST, "이미지 업로드를 실패했습니다."),
    ;

    private final HttpStatus status;
    private final String description;
}
