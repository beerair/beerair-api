package com.beerair.core.review.domain;

import com.beerair.core.common.domain.BaseEntity;
import com.beerair.core.common.util.IdGenerator;
import com.beerair.core.review.domain.vo.FeelStatus;
import com.beerair.core.review.domain.vo.ReviewFlavors;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import static com.beerair.core.common.util.IdGenerator.UUID_LENGTH;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {
    @Comment("Id")
    @Id
    @Column(length = UUID_LENGTH)
    private String id;

    @Comment("리뷰 내용")
    private String content;

    @Comment("출발 국가 Id")
    private Long departuresCountryId;

    @Comment("도착 국가 Id")
    private Long arrivalsCountryId;

    @Comment("맥주 평점")
    @Enumerated(value = EnumType.STRING)
    private FeelStatus feelStatus;

    @Comment("사진 URL")
    private String imageUrl;

    @Comment("리뷰 공개 여부")
    private Boolean isPublic;

    @Comment("맥주 맛 Id 최대 3개")
    @Embedded
    private ReviewFlavors flavorIds;

    @Comment("맥주 Id")
    private String beerId;

    @Comment("멤버 Id")
    private String memberId;

    @Builder
    private Review(String content, Long departuresCountryId, Long arrivalsCountryId, FeelStatus feelStatus, String imageUrl, Boolean isPublic, ReviewFlavors flavorIds, String beerId, String memberId) {
        this.id = IdGenerator.createUUID();
        this.content = content;
        this.departuresCountryId = departuresCountryId;
        this.arrivalsCountryId = arrivalsCountryId;
        this.feelStatus = feelStatus;
        this.imageUrl = imageUrl;
        this.isPublic = isPublic;
        this.flavorIds = flavorIds;
        this.beerId = beerId;
        this.memberId = memberId;
    }
}
