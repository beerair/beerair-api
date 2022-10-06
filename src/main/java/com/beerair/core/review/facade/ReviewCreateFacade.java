package com.beerair.core.review.facade;

import com.beerair.core.beer.infrastructure.BeerRepository;
import com.beerair.core.error.exception.beer.BeerNotFoundException;
import com.beerair.core.error.exception.region.CountryNotFoundException;
import com.beerair.core.event.ReviewCreateEventArgs;
import com.beerair.core.region.infrastructure.CountryRepository;
import com.beerair.core.review.domain.Review;
import com.beerair.core.review.domain.vo.ReviewFlavorIds;
import com.beerair.core.review.domain.vo.Route;
import com.beerair.core.review.dto.request.ReviewRequest;
import com.beerair.core.review.infrastructure.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class ReviewCreateFacade {
    private static final String DEFAULT_COUNTRY_NAME = "korea";
    private final CountryRepository countryRepository;
    private final ReviewRepository reviewRepository;
    private final BeerRepository beerRepository;

    private final ApplicationEventPublisher eventPublisher;

    public void create(String memberId, ReviewRequest request) {
        var review = reviewRepository.save(
                createReview(memberId, request)
        );
        eventPublisher.publishEvent(createEventArgs(review));
    }

    private Review createReview(String memberId, ReviewRequest request) {
        var latestReview = reviewRepository.findLatestByMemberId(memberId);

        var previousId = latestReview
                .map(Review::getId)
                .orElse(null);
        var route = createRoute(latestReview, request.getBeerId());
        var flavorIds = ReviewFlavorIds.from(request.getFlavorIds());

        return Review.builder()
                .memberId(memberId)
                .previousId(previousId)
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

    private Route createRoute(Optional<Review> latestReview, String beerId) {
        var latestRoute = latestReview.map(Review::getRoute)
                .orElseGet(this::defaultRoute);
        var arrivalCountryId = beerRepository.findCountryIdById(beerId)
                .orElseThrow(BeerNotFoundException::new);
        return Route.next(latestRoute, arrivalCountryId);
    }

    private Route defaultRoute() {
        var defaultCountry = countryRepository.findByEngName(DEFAULT_COUNTRY_NAME)
                .orElseThrow(CountryNotFoundException::new);
        return Route.ofOnlyArrival(defaultCountry.getId());
    }

    private ReviewCreateEventArgs createEventArgs(Review review) {
        return ReviewCreateEventArgs.builder()
                .reviewId(review.getId())
                .beerId(review.getBeerId())
                .memberId(review.getMemberId())
                .build();
    }
}
