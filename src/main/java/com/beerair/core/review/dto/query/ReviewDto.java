package com.beerair.core.review.dto.query;

import com.beerair.core.review.domain.vo.FeelStatus;

import java.time.LocalDateTime;

public interface ReviewDto {
    ReviewInfo getReview();
    BeerInfo getBeer();
    CountryInfo getDeparturesCountry();
    CountryInfo getArrivalCountry();
    FlavorInfo getFlavor1();
    FlavorInfo getFlavor2();
    FlavorInfo getFlavor3();

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

    interface FlavorInfo {
        String getContent();
    }
}
