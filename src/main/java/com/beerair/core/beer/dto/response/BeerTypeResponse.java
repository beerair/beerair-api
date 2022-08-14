package com.beerair.core.beer.dto.response;

import com.beerair.core.beer.domain.BeerType;
import com.beerair.core.beer.dto.query.BeerDto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
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

    public static BeerTypeResponse from(BeerDto.BeerTypeInfo beerTypeInfo) {
        return new BeerTypeResponse(beerTypeInfo.getId(), beerTypeInfo.getKorName(), beerTypeInfo.getEngName(),
                                    beerTypeInfo.getContent(),
                                    beerTypeInfo.getImageUrl());
    }
}
