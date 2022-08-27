package com.beerair.core.beer.dto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BeerSearchResponse {
    private List<BeerResponse> values;
    private int size;
    private long total;

    public static BeerSearchResponse from(List<BeerResponse> beers, long total) {
        return new BeerSearchResponse(beers, beers.size(), total);
    }
}
