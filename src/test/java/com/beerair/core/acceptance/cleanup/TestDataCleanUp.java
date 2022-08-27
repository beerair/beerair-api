package com.beerair.core.acceptance.cleanup;

import com.beerair.core.acceptance.beer.BeerSearchGivenDefs;
import com.beerair.core.beer.dto.request.BeerSearchRequest;
import org.springframework.stereotype.Component;

@Component
public class TestDataCleanUp implements CleanUp {
    @Override
    public void exec() {
        BeerSearchGivenDefs.searchRequest = new BeerSearchRequest();
    }
}
