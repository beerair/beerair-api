package com.beerair.core.beer.domain.vo.rs;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BeerTypeResponses {

    private final List<BeerTypeResponse> values;

    public static BeerTypeResponses from(List<BeerTypeResponse> values) {
        return new BeerTypeResponses(values);
    }
}
