package com.beerair.core.review.dto.query;

import com.beerair.core.review.domain.vo.FeelStatus;

import java.time.LocalDateTime;

public interface ReviewDto {
    ReviewInfo getReview();
    BeerInfo getBeer();
    CountryInfo getDeparturesCountry();
    CountryInfo getArrivalCountry();
    FlavorDto getFlavor1();
    FlavorDto getFlavor2();
    FlavorDto getFlavor3();

    interface ReviewInfo {
        String getId();
        String getContent();
        FeelStatus getFeelStatus();
        String getImageUrl();
        Boolean getIsPublic();
        LocalDateTime getCreatedAt();
    }

    interface BeerInfo {
        String getKorName();
        String getEngName();
        Float getAlcohol();
    }

    interface CountryInfo {
        String getKorName();
        String getEngName();
    }
}
