package com.beerair.core.cucumber.review;

import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import org.springframework.beans.factory.annotation.Autowired;

@ScenarioScope
public class FlavorStepWhenDefs {
    @Autowired
    private FlavorStepClient flavorStepClient;

    @When("{string} 맥주 맛 TOP3를 요청하면")
    public void 맥주_맛_TOP3_조회_요청(String beerId) {
        flavorStepClient.getFlavorTop3(beerId);
    }
}
