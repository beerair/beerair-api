package com.beerair.core.acceptance.review;

import com.beerair.core.review.domain.vo.FeelStatus;
import com.beerair.core.review.dto.request.ReviewRequest;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ScenarioScope
public class ReviewStepWhenDefs {
    @Autowired
    private ReviewStepClient reviewStepClient;

    @When("{string} 맥주에 맛 {int},{int},{int} 리뷰 작성을 요청하면")
    public void 맥주_좋아요_요청(String beerId, int flavor1, int flavor2, int flavor3) {
        ReviewRequest request = ReviewRequest.builder()
                .beerId(beerId)
                .content("안녕")
                .feelStatus(FeelStatus.GOOD)
                .flavorIds(List.of((long) flavor1, (long) flavor2, (long) flavor3))
                .imageUrl("홀롤로롱 이미지")
                .isPublic(true)
                .build();
        reviewStepClient.create(request);
    }

    @When("{string} 맥주 리뷰 조회를 요청하면")
    public void 맥주_리뷰_조회_요청(String beerId) {
        reviewStepClient.get(beerId);
    }
}
