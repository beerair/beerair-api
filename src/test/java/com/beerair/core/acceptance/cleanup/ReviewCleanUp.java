package com.beerair.core.acceptance.cleanup;

import com.beerair.core.beer.infrastructure.BeerRepository;
import com.beerair.core.review.infrastructure.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewCleanUp implements CleanUp {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void exec() {
        reviewRepository.deleteAll();
    }
}
