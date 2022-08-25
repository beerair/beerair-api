package com.beerair.core.acceptance.beer;

import com.beerair.core.acceptance.CucumberHttpResponseContext;
import com.beerair.core.common.dto.ResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.java.en.Then;
import io.cucumber.spring.ScenarioScope;

import static org.assertj.core.api.Assertions.assertThat;

@ScenarioScope
public class BeerLikeStepThenDefs {
    @Then("좋아요한 맥주 Count는 {int}개이다.")
    public void 좋아요한_맥주_Count_검증(int expert) {
        ResponseDto<Integer> response = CucumberHttpResponseContext.getBody(new TypeReference<>() {});
        assertThat(response.getData()).isEqualTo(expert);
    }
}
