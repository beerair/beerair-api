package com.beerair.core.cucumber.review;

import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import org.springframework.beans.factory.annotation.Autowired;

@ScenarioScope
public class FlavorStepWhenDefs {
    @Autowired
    private FlavorStepClient flavorStepClient;

    @When("{string} 맥주 맛 TOP{int}를 요청하면")
    public void 맥주_맛_RANK_조회_요청(String beerId, int rank) {
        flavorStepClient.getFlavorRank(beerId, rank);
    }
}
