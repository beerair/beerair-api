package com.beerair.core.review.dto.request;

import com.beerair.core.review.domain.Review;
import com.beerair.core.review.dto.FlavorIds;
import lombok.Data;

@Data
public class ReviewRequest {

    private final String content;
    private final Integer feel;
    private final String imageUrl;
    private final Boolean isPublic;
    private final FlavorIds flavorIds;
    private final Long beerId;

    public Review entity(Long departuresCountryId, Long arrivalsCountryId, Long memberId) {
        // TODO: flavorIds List to String 구현 예정
        return Review.of(content, departuresCountryId, arrivalsCountryId, feel, imageUrl, isPublic, flavorIds, memberId);
    }
}
