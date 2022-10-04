package com.beerair.core.region.domain;

import com.beerair.core.common.domain.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Country extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String backgroundImageUrl;

    private String imageUrl;

    private String korName;

    private String engName;

    private Long continentId;

    @Builder
    private Country(String backgroundImageUrl, String imageUrl, String korName, String engName, Long continentId) {
        this.backgroundImageUrl = backgroundImageUrl;
        this.imageUrl = imageUrl;
        this.korName = korName;
        this.engName = engName;
        this.continentId = continentId;
    }
}


