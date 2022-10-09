package com.beerair.core.cucumber.beer;

import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class BeerSearchWhenDefs {
    @Autowired
    private BeerStepClient beerStepClient;

    @When("맥주 검색을 요청하면")
    public void 맥주_검색_Submit() {
        beerStepClient.search(BeerSearchGivenDefs.searchRequest);
    }
}
