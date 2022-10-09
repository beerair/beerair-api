package com.beerair.core.beer.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BeerTypeResponses {
    private final List<BeerTypeResponse> values;

    public static BeerTypeResponses from(List<BeerTypeResponse> values) {
        return new BeerTypeResponses(values);
    }
}
