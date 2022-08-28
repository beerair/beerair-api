package com.beerair.core.acceptance.datasetup;

import com.beerair.core.beer.domain.Beer;
import com.beerair.core.beer.infrastructure.BeerRepository;
import com.beerair.core.fixture.Fixture;
import com.beerair.core.review.domain.Flavor;
import com.beerair.core.review.infrastructure.FlavorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class FlavorSetup extends DataSetup {
    @Autowired
    private FlavorRepository flavorRepository;

    @Transactional
    @Override
    protected void execute() {
        Flavor flavor1 = new Flavor("시원한 맛");
        Flavor flavor2 = new Flavor("달달한 맛");
        Flavor flavor3 = new Flavor("매운 맛");
        flavorRepository.saveAll(
                List.of(flavor1, flavor2, flavor3)
        );
    }
}
