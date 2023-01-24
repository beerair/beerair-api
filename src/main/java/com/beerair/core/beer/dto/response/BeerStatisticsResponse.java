package com.beerair.core.beer.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BeerStatisticsResponse {
    private final Long totalBeerCount;
    private final Long isActiveBeerCount;
    private final Long isDeletedBeerCount;
}
