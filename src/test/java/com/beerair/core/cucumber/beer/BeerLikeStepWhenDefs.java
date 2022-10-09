package com.beerair.core.cucumber.beer;

import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class BeerLikeStepWhenDefs {
    private static final String BEER_ID = "1234";

    @Autowired
    private BeerLikeStepClient beerLikeStepClient;

    @When("{string} 맥주 좋아요를 요청하면")
    public void 맥주_좋아요_요청(String id) {
        beerLikeStepClient.like(id);
    }
    @When("{string} 맥주 좋아요 해제를 요청하면")
    public void 맥주_좋아요_해제_요청(String id) {
        beerLikeStepClient.unlike(id);
    }

    @When("좋아요한 맥주 목록을 요청하면")
    public void 좋아요한_맥주_목록_요청() {
        beerLikeStepClient.getAll();
    }

    @When("좋아요한 맥주 Count를 요청하면")
    public void 맥주_Count_요청() {
        beerLikeStepClient.getCount();
    }
}
