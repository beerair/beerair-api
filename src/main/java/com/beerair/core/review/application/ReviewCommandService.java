package com.beerair.core.review.application;

import com.beerair.core.review.domain.Review;
import com.beerair.core.review.infrastructure.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ReviewCommandService {
    private final ReviewRepository reviewRepository;

    public void delete(String memberId, String reviewId) {
        var reviews = reviewRepository.findAllByIdAndPreviousIdAndMemberId(memberId, reviewId);
        if (reviews.isEmpty()) {
            return;
        }
        deleteCurrent(reviews);
        joinIfHasNext(reviews);
    }

    private void deleteCurrent(List<Review> reviews) {
        var current = reviews.get(0);
        current.delete();
    }

    private void joinIfHasNext(List<Review> reviews) {
        if (reviews.size() <= 1) {
            return;
        }
        var current = reviews.get(0);
        var next = reviews.get(1);
        next.joinRoute(current);
    }
}
