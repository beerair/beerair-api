package com.beerair.core.suggest.dto.response;

import lombok.Data;

@Data
public class BeerSuggestCountResponse {
    private final Long memberId;
    private final Long count;
}
