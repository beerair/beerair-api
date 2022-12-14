package com.beerair.core.cucumber.review;

import static org.assertj.core.api.Assertions.assertThat;

import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.cucumber.CucumberHttpResponseContext;
import com.beerair.core.review.dto.response.ReviewResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.java.en.Then;
import io.cucumber.spring.ScenarioScope;

@ScenarioScope
public class ReviewStepThenDefs {
    @Then("리뷰의 출발지는 {string} 이다.")
    public void 출발지_검증(String expert) {
        ResponseDto<ReviewResponse> response = CucumberHttpResponseContext.getBody(new TypeReference<>() {});
        var departures = response
                .getData()
                .getDeparturesCountry()
                .getKorName();
        assertThat(departures).isEqualTo(expert);
    }

    @Then("리뷰의 도착지는 {string} 이다.")
    public void 도착지_검증(String expert) {
        ResponseDto<ReviewResponse> response = CucumberHttpResponseContext.getBody(new TypeReference<>() {});
        var arrival = response
                .getData()
                .getArrivalCountry()
                .getKorName();
        assertThat(arrival).isEqualTo(expert);
    }
}
