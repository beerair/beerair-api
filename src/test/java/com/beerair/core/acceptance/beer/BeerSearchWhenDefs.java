package com.beerair.core.acceptance.beer;

import com.beerair.core.beer.dto.request.BeerSearchRequest;
import com.beerair.core.beer.infrastructure.BeerOrderBy;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import org.springframework.beans.factory.annotation.Autowired;

@ScenarioScope
public class BeerSearchWhenDefs {
    @Autowired
    private BeerStepClient beerStepClient;

    @When("맥주 검색을 요청하면")
    public void 맥주_검색_Submit() {
        beerStepClient.search(
                BeerSearchGivenDefs.searchRequest
        );
    }
}
