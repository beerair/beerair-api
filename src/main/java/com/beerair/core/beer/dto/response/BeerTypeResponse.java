package com.beerair.core.beer.dto.response;

import com.beerair.core.beer.domain.BeerType;
import com.beerair.core.beer.dto.query.BeerDto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BeerTypeResponse {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String korName;

    @JsonProperty
    private String engName;

    @JsonProperty
    private String content;

    @JsonProperty
    private String imageUrl;

    public static BeerTypeResponse from(BeerType beerType) {
        return new BeerTypeResponse(beerType.getId(), beerType.getKorName(), beerType.getEngName(), beerType.getContent(), beerType.getImageUrl());
    }

    public static BeerTypeResponse from(BeerDto.BeerTypeInfo beerTypeInfo) {
        return new BeerTypeResponse(beerTypeInfo.getId(), beerTypeInfo.getKorName(), beerTypeInfo.getEngName(),
                                    beerTypeInfo.getContent(),
                                    beerTypeInfo.getImageUrl());
    }
}
