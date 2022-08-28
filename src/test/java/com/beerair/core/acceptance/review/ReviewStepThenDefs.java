package com.beerair.core.acceptance.review;

import com.beerair.core.acceptance.CucumberHttpResponseContext;
import com.beerair.core.beer.dto.response.BeerSearchResponse;
import com.beerair.core.common.dto.ResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.java.en.Then;
import io.cucumber.spring.ScenarioScope;

import static org.assertj.core.api.Assertions.assertThat;

@ScenarioScope
public class ReviewStepThenDefs {
    @Then("리뷰의 출발지는 {string} 이다.")
    public void 출발지_검증(String expert) {
        assertThat(false).isTrue();
    }

    @Then("리뷰의 도착지는 {string} 이다.")
    public void 도착지_검증(String expert) {
        assertThat(false).isTrue();
    }
}
