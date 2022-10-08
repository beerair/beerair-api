package com.beerair.core.beer.dto.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BeerTypeResponses {
    @JsonUnwrapped
    private final List<BeerTypeResponse> values;

    public static BeerTypeResponses from(List<BeerTypeResponse> values) {
        return new BeerTypeResponses(values);
    }
}
