package com.beerair.core.review.facade;

import com.beerair.core.beer.infrastructure.BeerRepository;
import com.beerair.core.error.exception.beer.BeerNotFoundException;
import com.beerair.core.error.exception.region.CountryNotFoundException;
import com.beerair.core.region.infrastructure.CountryRepository;
import com.beerair.core.review.domain.Review;
import com.beerair.core.review.domain.vo.ReviewFlavorIds;
import com.beerair.core.review.domain.vo.Route;
import com.beerair.core.review.dto.request.ReviewRequest;
import com.beerair.core.review.infrastructure.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class ReviewCreateFacade {
    private static final String DEFAULT_COUNTRY_NAME = "korea";
    private final CountryRepository countryRepository;
    private final ReviewRepository reviewRepository;
    private final BeerRepository beerRepository;

    public void create(String memberId, ReviewRequest request) {
        reviewRepository.save(
                createReview(memberId, request)
        );
    }

    private Review createReview(String memberId, ReviewRequest request) {
        var route = createRoute(memberId, request.getBeerId());
        var flavorIds = ReviewFlavorIds.from(request.getFlavorIds());
        return Review.builder()
                .memberId(memberId)
                .route(route)
                .flavorIds(flavorIds)
                .beerId(request.getBeerId())
                .content(request.getContent())
                .beerId(request.getBeerId())
                .feelStatus(request.getFeelStatus())
                .imageUrl(request.getImageUrl())
                .isPublic(request.getIsPublic())
                .build();
    }

    private Route createRoute(String memberId, String beerId) {
        Long arrivalCountryId = beerRepository.findCountryIdById(beerId)
                .orElseThrow(BeerNotFoundException::new);
        var latestRoute = reviewRepository
                .findLatestRouteByMemberId(memberId)
                .orElseGet(this::defaultRoute);
        return Route.next(latestRoute, arrivalCountryId);
    }

    private Route defaultRoute() {
        var defaultCountry = countryRepository.findByEngName(DEFAULT_COUNTRY_NAME)
                .orElseThrow(CountryNotFoundException::new);
        return Route.ofOnlyArrival(defaultCountry.getId());
    }
}
