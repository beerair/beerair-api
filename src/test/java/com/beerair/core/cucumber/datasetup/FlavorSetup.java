package com.beerair.core.cucumber.datasetup;

import com.beerair.core.review.domain.Flavor;
import com.beerair.core.review.infrastructure.FlavorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FlavorSetup extends DataSetup {
    @Autowired
    private FlavorRepository flavorRepository;

    @Transactional
    @Override
    protected void execute() {
        Flavor flavor1 = new Flavor("1 맛");
        Flavor flavor2 = new Flavor("2 맛");
        Flavor flavor3 = new Flavor("3 맛");
        Flavor flavor4 = new Flavor("4 맛");
        Flavor flavor5 = new Flavor("5 맛");
        Flavor flavor6 = new Flavor("6 맛");
        flavorRepository.saveAll(
                List.of(flavor1, flavor2, flavor3, flavor4, flavor5, flavor6)
        );
    }
}
