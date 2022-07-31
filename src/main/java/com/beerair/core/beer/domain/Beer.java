package com.beerair.core.beer.domain;

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
public class Beer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float alcohol;

    private String content;

    private String ImageUrl;

    private String korName;

    private String engName;

    private Long typeId;

    private Integer volume;

    private Integer price;

    private Long countryId;
}

