package com.beerair.core.acceptance.datasetup;

import com.beerair.core.beer.infrastructure.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.beerair.core.fixture.BeerFixture.createBeerFixture;

@Component
public class BeerSetup extends DataSetup {
    public static final String BEER_ID = "1234";

    @Autowired
    private BeerRepository beerRepository;

    @Override
    protected void execute() {
        var beer = createBeerFixture()
                .set("id", BEER_ID)
                .get();
        beerRepository.save(beer);
    }
}
