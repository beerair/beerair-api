package com.beerair.core.review.application;

import com.beerair.core.error.exception.review.ReviewNotFoundException;
import com.beerair.core.review.dto.response.ReviewResponse;
import com.beerair.core.review.infrastructure.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public ReviewResponse get(String memberId, String beerId) {
        var result = reviewRepository.findByMemberIdAndBeerId(memberId, beerId)
                .orElseThrow(ReviewNotFoundException::new);
        return ReviewResponse.from(result);
    }
}
