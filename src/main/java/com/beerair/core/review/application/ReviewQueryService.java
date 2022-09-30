package com.beerair.core.review.application;

import com.beerair.core.error.exception.review.ReviewNotFoundException;
import com.beerair.core.review.dto.response.ReviewResponse;
import com.beerair.core.review.infrastructure.ReviewQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ReviewResponse> getAllByMe(String memberId, int limit) {
        Pageable pageable = Pageable.ofSize(limit);
        return reviewQueryRepository.findAllByMemberId(memberId, pageable)
            .stream()
            .map(ReviewResponse::ofListItemAtMe)
            .collect(Collectors.toList());
    }

    public List<ReviewResponse> getAllByBeer(String beerId) {
        return reviewQueryRepository.findAllByBeerId(beerId)
                .stream()
                .map(ReviewResponse::ofListItemAtBeer)
                .collect(Collectors.toList());
    }
}
