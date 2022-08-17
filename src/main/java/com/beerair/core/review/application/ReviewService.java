package com.beerair.core.review.application;

import com.beerair.core.error.exception.review.ReviewNotFoundException;
import com.beerair.core.review.dto.request.ReviewRequest;
import com.beerair.core.review.infrastructure.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;


    public void create(ReviewRequest request, Long arrivalsCountryId, Long memberId) {

        Long departureCountryId = reviewRepository.findAllByMemberIdOrderByIdDesc(memberId)
                .stream()
                .findFirst()
                .orElseThrow(ReviewNotFoundException::new)
                .getArrivalsCountryId();

        reviewRepository.save(
                request.entity(request, departureCountryId, arrivalsCountryId, memberId));
    }
}
