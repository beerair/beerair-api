package com.beerair.core.cucumber.cleanup;

import com.beerair.core.beer.infrastructure.BeerLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeerLikeCleanUp implements CleanUp {
    @Autowired
    private BeerLikeRepository beerLikeRepository;

    @Override
    public void exec() {
        beerLikeRepository.deleteAll();
    }
}
