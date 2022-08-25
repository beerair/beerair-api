package com.beerair.core.review.domain;

import com.beerair.core.common.domain.BaseEntity;
import com.beerair.core.review.domain.vo.FeelStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {
    @Comment("Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Comment("맥주 맛 Id (최대 3개)")
    @Embedded
    @AttributeOverride(name = "values", column = @Column(name = "flavor_ids", columnDefinition = "맥주 맛 Id (최대 3개)"))
    private FlavorIds flavorIds;

    @Comment("맥주 Id")
    private Long beerId;

    @Comment("멤버 Id")
    private String memberId;

    private Review(String content, Long departuresCountryId, Long arrivalsCountryId, FeelStatus feelStatus, String imageUrl, Boolean isPublic, FlavorIds flavorIds, Long beerId, String memberId) {
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

    public static Review of(String content, Long departuresCountryId, Long arrivalsCountryId, FeelStatus feelStatus, String imageUrl, Boolean isPublic, FlavorIds flavorIds, Long beerId, String memberId) {
        return new Review(content, departuresCountryId, arrivalsCountryId, feelStatus, imageUrl, isPublic, flavorIds, beerId, memberId);
    }
}
