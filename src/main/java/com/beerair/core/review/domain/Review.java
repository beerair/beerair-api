package com.beerair.core.review.domain;

import com.beerair.core.common.domain.BaseEntity;
import com.beerair.core.review.domain.vo.FeelStatus;
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

    private FeelStatus feelStatus;

    private String imageUrl;

    private Boolean isPublic;

    private String flavorIds;

    private Long beerId;

    private String memberId;
}
