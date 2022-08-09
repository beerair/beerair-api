package com.beerair.core.beer.domain.vo.rs;

import com.beerair.core.beer.domain.BeerType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BeerTypeResponse {

    @JsonProperty
    private final Long id;

    @JsonProperty
    private final String korName;

    @JsonProperty
    private final String engName;

    @JsonProperty
    private final String content;

    @JsonProperty
    private final String imageUrl;

    public static BeerTypeResponse from(BeerType beerType) {
        return new BeerTypeResponse(beerType.getId(), beerType.getKorName(), beerType.getEngName(), beerType.getContent(), beerType.getImageUrl());
    }
}
