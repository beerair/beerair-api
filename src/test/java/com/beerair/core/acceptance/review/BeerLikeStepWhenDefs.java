package com.beerair.core.acceptance.review;

import com.beerair.core.review.domain.vo.FeelStatus;
import com.beerair.core.review.dto.request.ReviewRequest;
import io.cucumber.java.en.Given;
import io.cucumber.spring.ScenarioScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ScenarioScope
public class BeerLikeStepWhenDefs {
    @Autowired
    private ReviewStepClient reviewStepClient;

    @Given("{string} 맥주에 리뷰 작성을 요청하면")
    public void 맥주_좋아요_요청(String beerId) {
        ReviewRequest request = ReviewRequest.builder()
                .beerId(beerId)
                .content("안녕")
                .feelStatus(FeelStatus.GOOD)
                .flavorIds(List.of(1L, 2L, 3L))
                .imageUrl("홀롤로롱 이미지")
                .build();
        reviewStepClient.create(request);
    }

    @Given("{string} 맥주 리뷰 조회를 요청하면")
    public void 맥주_리뷰_조회_요청(String beerId) {
        reviewStepClient.get(beerId);
    }
}
