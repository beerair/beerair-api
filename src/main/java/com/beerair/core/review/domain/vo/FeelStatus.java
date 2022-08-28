package com.beerair.core.review.domain.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FeelStatus {
    VERY_BAD(1, "별로"),
    BAD(2, "애매"),
    USUALLY(3, "보통"),
    GOOD(4, "좋음"),
    VERY_GOOD(5, "최고");

    private final int score;
    private final String description;
}
