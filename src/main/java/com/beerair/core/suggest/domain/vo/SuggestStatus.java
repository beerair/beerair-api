package com.beerair.core.suggest.domain.vo;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuggestStatus {
    PROCEED("준비"),
    REJECT("거절"),
    COMPLETE("반영완료"),
    ;

    private final String description;
}
