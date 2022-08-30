package com.beerair.core.review.application;

import com.beerair.core.error.exception.review.ReviewNotFoundException;
import com.beerair.core.review.dto.response.ReviewResponse;
import com.beerair.core.review.infrastructure.ReviewQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewQueryService {
    private final ReviewQueryRepository reviewQueryRepository;

    public ReviewResponse get(String memberId, String reviewId) {
        var result = reviewQueryRepository.findByMemberIdAndReviewId(memberId, reviewId)
                .orElseThrow(ReviewNotFoundException::new);
        return ReviewResponse.from(result);
    }
}
