package com.beerair.core.beer.dto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BeerResponses {
    private List<BeerResponse> values;
    private int size;
    private long total;

    public static BeerResponses from(List<BeerResponse> beers, long total) {
        return new BeerResponses(beers, beers.size(), total);
    }
}
