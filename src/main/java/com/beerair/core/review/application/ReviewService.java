package com.beerair.core.review.application;

import com.beerair.core.error.exception.region.CountryNotFoundException;
import com.beerair.core.region.infrastructure.CountryRepository;
import com.beerair.core.review.domain.Review;
import com.beerair.core.review.dto.request.ReviewRequest;
import com.beerair.core.review.infrastructure.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final CountryRepository countryRepository;
    private static final String KOREA_ENG_NAME = "korea";

    @Transactional
    public void create(ReviewRequest request, Long arrivalsCountryId, String memberId) {
        Optional<Review> reviewOpt = reviewRepository.findAllByMemberIdOrderByIdDesc(memberId)
                .stream()
                .findFirst();

        if (reviewOpt.isEmpty()) {
            saveWithDefaultDepartureCountry(request, arrivalsCountryId, memberId);
            return;
        }

        reviewRepository.save(
                request.entity(reviewOpt.get().getDeparturesCountryId(), arrivalsCountryId, memberId));
    }

    private void saveWithDefaultDepartureCountry(ReviewRequest request, Long arrivalsCountryId, String memberId) {
        var departureCountryId = countryRepository.findByEngName(KOREA_ENG_NAME)
                .orElseThrow(CountryNotFoundException::new)
                .getId();

        reviewRepository.save(
                request.entity(departureCountryId, arrivalsCountryId, memberId));
    }
}
