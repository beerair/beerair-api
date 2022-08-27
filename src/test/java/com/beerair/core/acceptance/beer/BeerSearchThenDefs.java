package com.beerair.core.acceptance.beer;

import com.beerair.core.acceptance.CucumberHttpResponseContext;
import com.beerair.core.beer.dto.response.BeerSearchResponse;
import com.beerair.core.common.dto.ResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.java.en.Then;
import io.cucumber.spring.ScenarioScope;

import static org.assertj.core.api.Assertions.assertThat;

@ScenarioScope
public class BeerSearchThenDefs {
    @Then("맥주 검색 결과의 검색된 갯수는 {int}개 이다.")
    public void 검색_결과_갯수_검증(int expert) {
        ResponseDto<BeerSearchResponse> response = CucumberHttpResponseContext.getBody(new TypeReference<>() {});
        assertThat(response.getData().getSize()).isEqualTo(expert);
    }

    @Then("맥주 검색 결과의 {int}번 맥주의 이름은 {string} 이다.")
    public void 검색_검증(int index, String name) {
        ResponseDto<BeerSearchResponse> response = CucumberHttpResponseContext.getBody(new TypeReference<>() {});
        var value = response.getData().getValues().get(index);
        assertThat(value.getKorName()).isEqualTo(name);
    }
}