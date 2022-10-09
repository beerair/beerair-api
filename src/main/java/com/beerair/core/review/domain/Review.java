package com.beerair.core.review.domain;

import com.beerair.core.common.domain.BaseEntity;
import com.beerair.core.common.util.KeyGenerator;
import com.beerair.core.review.domain.vo.FeelStatus;
import com.beerair.core.review.domain.vo.ReviewFlavorIds;
import com.beerair.core.review.domain.vo.Route;
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
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static com.beerair.core.common.util.KeyGenerator.UUID_LENGTH;

@Table(
        indexes = {
                @Index(name = "INDEX_BEER_ID", columnList = "beerId"),
                @Index(name = "INDEX_PREVIOUS_ID", columnList = "previousId"),
                @Index(name = "INDEX_MEMBER_ID", columnList = "memberId"),
                @Index(name = "INDEX_FLAVOR1_ID", columnList = "flavor1"),
                @Index(name = "INDEX_FLAVOR2_ID", columnList = "flavor2"),
                @Index(name = "INDEX_FLAVOR3_ID", columnList = "flavor3")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "UNIQUE_MEMBER_BEER", columnNames = {
                        "memberId", "beerId"
                })
        }
)
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {
    @Comment("Id")
    @Id
    @Column(length = UUID_LENGTH)
    private String id;

    @Comment("이전 리뷰 맥주 Id")
    @Column(length = UUID_LENGTH)
    private String previousId;

    @Comment("맥주 Id")
    @Column(nullable = false)
    private String beerId;

    @Comment("멤버 Id")
    @Column(nullable = false)
    private String memberId;

    @Embedded
    private Route route;

    @Comment("맥주 평점")
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private FeelStatus feelStatus;

    @Comment("맥주 맛 Id 최대 3개")
    @Embedded
    private ReviewFlavorIds flavorIds;

    @Comment("리뷰 내용")
    private String content;

    @Comment("사진 URL")
    private String imageUrl;

    @Comment("리뷰 공개 여부")
    @Column(nullable = false)
    private Boolean isPublic;

    @Builder
    private Review(String previousId, String beerId, String memberId, Route route, FeelStatus feelStatus, ReviewFlavorIds flavorIds, String content, String imageUrl, Boolean isPublic) {
        this.id = KeyGenerator.createKeyByUUID();
        this.previousId = previousId;
        this.beerId = beerId;
        this.memberId = memberId;
        this.route = route;
        this.feelStatus = feelStatus;
        this.flavorIds = flavorIds;
        this.content = content;
        this.imageUrl = imageUrl;
        this.isPublic = isPublic;
    }

    public void joinRoute(Review previousReview) {
        route = previousReview
                .getRoute()
                .join(getRoute());
    }

    public void delete() {
        super.delete();
    }
}
