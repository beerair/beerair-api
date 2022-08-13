package com.beerair.core.beer.domain.vo.rs;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BeerResponses {

    private final List<BeerResponse> values;

    public static BeerResponses from(List<BeerResponse> beerResponses) {
        return new BeerResponses(beerResponses);
    }
}
