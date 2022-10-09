package com.beerair.core.beer.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BeerRecommendResponse {
    private final List<BeerResponse> beers;
}