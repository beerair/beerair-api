package com.beerair.core.cucumber.review;

import com.beerair.core.cucumber.CucumberHttpResponseContext;
import com.beerair.core.error.exception.review.ReviewNotFoundException;
import com.beerair.core.review.domain.Review;
import com.beerair.core.review.domain.vo.FeelStatus;
import com.beerair.core.review.dto.request.ReviewRequest;
import com.beerair.core.review.infrastructure.ReviewRepository;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import static com.beerair.core.cucumber.CucumberHttpResponseContext.*;

@ScenarioScope
public class ReviewStepWhenDefs {
    @Autowired
    private ReviewStepClient reviewStepClient;
    @Autowired
    private ReviewRepository reviewRepository;

    @When("{int} 맥주에 맛 {int},{int},{int} 리뷰 작성을 요청하면")
    public void 맥주_좋아요_요청(Integer beerId, int flavor1, int flavor2, int flavor3) {
        ReviewRequest request = ReviewRequest.builder()
                .beerId(beerId)
                .content("안녕")
                .feelStatus(FeelStatus.GOOD.getScore())
                .flavorIds(List.of((long) flavor1, (long) flavor2, (long) flavor3))
                .imageUrl("홀롤로롱 이미지")
                .isPublic(true)
                .build();
        reviewStepClient.create(request);
    }

    @When("{int} 맥주 나의 리뷰 조회를 요청하면")
    public void 맥주_리뷰_조회_요청(Integer beerId) {
            reviewStepClient.get(getReviewId(beerId));
    }

    @When("{int} 맥주 나의 리뷰 삭제를 요청하면")
    public void 맥주_리뷰_삭제_요청(Integer beerId) {
        reviewStepClient.delete(getReviewId(beerId));
    }

    @When("나의 맥주 리뷰 목록을 조회하면")
    public void 나의_맥주_리뷰_목록_요청() {
        reviewStepClient.getAllByMe();
    }

    @When("{string} 맥주 리뷰 목록 조회를 요청하면")
    public void 특정_맥주_리뷰_목록_요청(Integer beerId) {
        reviewStepClient.getAllByBeer(beerId, getNextCursor(), null);
    }

    @When("{int} 맥주 리뷰 목록 {int}개 조회를 요청하면")
    public void 특정_맥주_리뷰_목록_요청(Integer beerId, Integer limit) {
        reviewStepClient.getAllByBeer(beerId, getNextCursor(), limit);
    }

    @When("최근 리뷰 조회 {int}개를 요청하면")
    public void 최근_맥주_리뷰_조회_요청(int limit) {
        reviewStepClient.getRecentByMe(limit);
    }

    private Integer getReviewId(Integer beerId) {
        return reviewRepository.findByBeerId(beerId)
                .map(Review::getId)
                .orElseThrow(ReviewNotFoundException::new);
    }
}
