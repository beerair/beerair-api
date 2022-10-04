package com.beerair.core.cucumber.beer;

import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class BeerWhenDefs {
    @Autowired
    private BeerStepClient beerStepClient;

    @When("맥주 추천 목록을 요청하면")
    public void 맥주_추천_목록_요청() {
        beerStepClient.getRecommends();
    }
}
