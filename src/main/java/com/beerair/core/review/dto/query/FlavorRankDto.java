package com.beerair.core.review.dto.query;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FlavorRankDto {
    private final Long id;
    private final String content;
    private final Long count;
}
