package com.beerair.core.review.facade;

import com.beerair.core.beer.infrastructure.BeerRepository;
import com.beerair.core.error.exception.beer.BeerNotFoundException;
import com.beerair.core.error.exception.region.CountryNotFoundException;
import com.beerair.core.region.infrastructure.CountryRepository;
import com.beerair.core.review.domain.Review;
import com.beerair.core.review.dto.request.ReviewRequest;
import com.beerair.core.review.infrastructure.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewCreateFacade {
    private static final String KOREA_ENG_NAME = "korea";
    private final BeerRepository beerRepository;
    private final ReviewRepository reviewRepository;
    private final CountryRepository countryRepository;

    public void create(String memberId, ReviewRequest request) {
        Review review = request.entity(
                memberId,
                departureCountryId(memberId),
                arrivalsCountryId(request.getBeerId())
        );

        reviewRepository.save(review);
    }

    private Long arrivalsCountryId(String beerId) {
        return beerRepository
                .findCountryIdById(beerId)
                .orElseThrow(BeerNotFoundException::new);
    }

    private Long departureCountryId(String memberId) {
        return reviewRepository
                .findTop1ByMemberIdOrderByCreatedAtDesc(memberId)
                .map(Review::getArrivalsCountryId)
                .orElseGet(this::defaultDepartureId);
    }

    private Long defaultDepartureId() {
        return countryRepository
                .findByEngName(KOREA_ENG_NAME)
                .orElseThrow(CountryNotFoundException::new)
                .getId();
    }
}
