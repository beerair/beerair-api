package com.beerair.core.cucumber.cleanup;

import com.beerair.core.cucumber.beer.BeerSearchGivenDefs;
import com.beerair.core.beer.dto.request.BeerSearchRequest;
import org.springframework.stereotype.Component;

@Component
public class TestDataCleanUp implements CleanUp {
    @Override
    public void exec() {
        BeerSearchGivenDefs.searchRequest = new BeerSearchRequest();
    }
}
