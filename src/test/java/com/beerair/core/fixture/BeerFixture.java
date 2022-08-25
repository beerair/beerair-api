package com.beerair.core.fixture;

import com.beerair.core.beer.domain.Beer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BeerFixture {
    public static Fixture<Beer> createBeerFixture() {
        var beer = Beer.builder()
                .build();
        return new Fixture<>(beer);
    }
}
