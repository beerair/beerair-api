package com.beerair.core.review.dto.response;

import com.beerair.core.beer.dto.query.BeerDto;
import com.beerair.core.review.domain.Review;
import com.beerair.core.review.domain.vo.FeelStatus;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReviewResponse {
    private final String id;
    private final String content;
    /* TODO
    private Long departuresCountryId;
    private Long arrivalsCountryId;

     */
    private final List<Long> flavorIds;
    private final FeelStatus feelStatus;
    private final String imageUrl;
    private final LocalDateTime createdAt;

    public static ReviewResponse from(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .content(review.getContent())
                .imageUrl(review.getImageUrl())
                .feelStatus(review.getFeelStatus())
                .flavorIds(review.getFlavorIds().getValues())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResponse from(BeerDto.ReviewInfo review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .content(review.getContent())
                .imageUrl(review.getImageUrl())
                .feelStatus(review.getFeelStatus())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResponse ofListItem(BeerDto.ReviewInfo review) {
        return ReviewResponse.builder()
                .feelStatus(review.getFeelStatus())
                .build();
    }
}
