package com.beerair.core.beer.domain;

import com.beerair.core.common.domain.BaseEntity;
import com.beerair.core.common.util.IdGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BeerType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String korName;

    private String engName;

    private String content;

    private String imageUrl;

    @Builder
    private BeerType(String korName, String engName, String content, String imageUrl) {
        this.korName = korName;
        this.engName = engName;
        this.content = content;
        this.imageUrl = imageUrl;
    }
}
