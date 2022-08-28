package com.beerair.core.review.application;

import com.beerair.core.error.exception.review.ReviewNotFoundException;
import com.beerair.core.review.dto.query.ReviewDto;
import com.beerair.core.review.dto.response.FlavorResponse;
import com.beerair.core.review.dto.response.ReviewResponse;
import com.beerair.core.review.infrastructure.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewResponse get(String memberId, String beerId) {
        var result = reviewRepository.findByMemberIdAndBeerId(memberId, beerId)
                .orElseThrow(ReviewNotFoundException::new);
        return ReviewResponse.from(result);
    }
}
