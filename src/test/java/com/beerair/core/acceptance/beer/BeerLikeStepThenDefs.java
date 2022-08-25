package com.beerair.core.acceptance.beer;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;

@ScenarioScope
public class BeerLikeStepThenDefs {
    @Then("조회된 맥주 Count는 {int}개이다.")
    public void 맥주_좋아요_요청(int expert) {

    }
}
