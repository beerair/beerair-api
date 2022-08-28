package com.beerair.core.acceptance.review;

import com.beerair.core.acceptance.CucumberHttpResponseContext;
import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.review.dto.response.FlavorResponse;
import com.beerair.core.review.dto.response.ReviewResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.java.en.Then;
import io.cucumber.spring.ScenarioScope;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ScenarioScope
public class FlavorStepThenDefs {
    @Then("맛 TOP3는 {int},{int},{int} 이다.")
    public void 도착지_검증(int f1, int f2, int f3) {
        Set<Long> experts = Set.of((long) f1, (long) f2, (long) f3);
        ResponseDto<List<FlavorResponse>> response = CucumberHttpResponseContext.getBody(new TypeReference<>() {});

        for (var each : response.getData()) {
            assertThat(experts).contains(each.getId());
        }
    }
}
