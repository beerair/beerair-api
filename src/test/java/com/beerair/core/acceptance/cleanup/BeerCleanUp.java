package com.beerair.core.acceptance.cleanup;

import com.beerair.core.beer.infrastructure.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeerCleanUp implements CleanUp {
    @Autowired
    private BeerRepository beerRepository;

    @Override
    public void exec() {
        beerRepository.deleteAll();
    }
}
