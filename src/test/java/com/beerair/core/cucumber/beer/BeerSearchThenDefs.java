package com.beerair.core.cucumber.beer;

import static org.assertj.core.api.Assertions.assertThat;

import com.beerair.core.beer.dto.response.BeerSearchResponse;
import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.cucumber.CucumberHttpResponseContext;
import com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.java.en.Then;

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
