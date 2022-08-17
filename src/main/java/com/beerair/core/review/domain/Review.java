package com.beerair.core.review.domain;

import com.beerair.core.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Long departuresCountryId;

    private Long arrivalsCountryId;

    private Integer feel;

    private String imageUrl;

    private Boolean isPublic;

    private String flavorIds;

    private Long beerId;

    private String memberId;

    private Review(String content, Long departuresCountryId, Long arrivalsCountryId, Integer feel, String imageUrl, Boolean isPublic, String flavorIds, Long beerId, String memberId) {
        this.content = content;
        this.departuresCountryId = departuresCountryId;
        this.arrivalsCountryId = arrivalsCountryId;
        this.feel = feel;
        this.imageUrl = imageUrl;
        this.isPublic = isPublic;
        this.flavorIds = flavorIds;
        this.beerId = beerId;
        this.memberId = memberId;
    }

    public static Review of(String content, Long departuresCountryId, Long arrivalsCountryId, Integer feel, String imageUrl, Boolean isPublic, String flavorIds, Long beerId, String memberId) {
        return new Review(content, departuresCountryId, arrivalsCountryId, feel, imageUrl, isPublic, flavorIds, beerId, memberId);
    }
}
