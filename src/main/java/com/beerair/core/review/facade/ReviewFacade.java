package com.beerair.core.review.facade;

import com.beerair.core.beer.application.BeerService;
import com.beerair.core.common.util.SecurityUtil;
import com.beerair.core.review.application.ReviewService;
import com.beerair.core.review.dto.request.ReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewFacade {
    private final ReviewService reviewService;
    private final BeerService beerService;

    public void create(ReviewRequest request) {
        var memberId = SecurityUtil.getMemberId();

        var arrivalsCountryId = beerService.getWithRegion(request.getBeerId())
                .getCountry().getId();

        reviewService.create(request, arrivalsCountryId, memberId);
    }
}
