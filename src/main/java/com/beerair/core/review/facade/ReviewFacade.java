package com.beerair.core.review.facade;

import com.beerair.core.beer.application.BeerService;
import com.beerair.core.common.util.SecurityUtil;
import com.beerair.core.review.application.ReviewService;
import com.beerair.core.review.dto.request.ReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewFacade {
    private final ReviewService reviewService;
    private final BeerService beerService;

    public void create(String memberId, ReviewRequest request) {
        var arrivalsCountryId = beerService.getWithRegion(memberId, request.getBeerId())
                .getCountry().getId();

        reviewService.create(request, arrivalsCountryId, memberId);
    }
}
