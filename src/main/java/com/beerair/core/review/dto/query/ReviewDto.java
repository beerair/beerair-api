package com.beerair.core.review.dto.query;

import com.beerair.core.review.domain.vo.FeelStatus;
import com.beerair.core.review.domain.vo.Route;
import java.time.LocalDateTime;

public interface ReviewDto {
    ReviewInfo getReview();
    BeerInfo getBeer();
    CountryInfo getDeparturesCountry();
    CountryInfo getArrivalCountry();
    MemberInfo getMember();
    FlavorDto getFlavor1();
    FlavorDto getFlavor2();
    FlavorDto getFlavor3();

    interface ReviewInfo {
        Integer getId();
        String getPreviousId();
        String getContent();
        FeelStatus getFeelStatus();
        String getImageUrl();
        Route getRoute();
        LocalDateTime getCreatedAt();
    }

    interface MemberInfo {
        String getId();
        String getNickname();
    }

    interface BeerInfo {
        Integer getId();
        String getKorName();
        String getEngName();
        Float getAlcohol();
    }

    interface CountryInfo {
        Long getId();
        String getKorName();
        String getEngName();
    }
}
