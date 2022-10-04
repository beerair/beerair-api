package com.beerair.core.cucumber.beer;

import static org.assertj.core.api.Assertions.assertThat;

import com.beerair.core.beer.dto.response.BeerResponse;
import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.cucumber.CucumberHttpResponseContext;
import com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.java.en.Then;
import java.util.List;

public class BeerLikeStepThenDefs {
    @Then("좋아요한 맥주 Count는 {int}개이다.")
    public void 좋아요한_맥주_Count_검증(int expert) {
        ResponseDto<Integer> response = CucumberHttpResponseContext.getBody(new TypeReference<>() {});
        assertThat(response.getData()).isEqualTo(expert);
    }

    @Then("맥주 목록에서 첫번째 맥주에 리뷰가 존재한다.")
    public void 맥주_목록에서_리뷰_검증() {
        ResponseDto<List<BeerResponse>> response = CucumberHttpResponseContext.getBody(new TypeReference<>() {});
        var first = response.getData().get(0);
        assertThat(first.getMyReview()).isNotNull();
    }
}
