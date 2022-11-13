package com.beerair.core.review.domain;

import com.beerair.core.common.domain.BaseEntity;
import com.beerair.core.common.util.KeyGenerator;
import com.beerair.core.review.domain.vo.FeelStatus;
import com.beerair.core.review.domain.vo.ReviewFlavorIds;
import com.beerair.core.review.domain.vo.Route;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
                @Index(name = "INDEX_REVIEW_CURSOR_PAGING", columnList = "beerId, deletedAt"),
                @Index(name = "INDEX_REVIEW_MEMBER_ID", columnList = "memberId")
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = UUID_LENGTH)
    private Integer id;

    @Comment("이전 리뷰 맥주 Id")
    @Column
    private Integer previousId;

    @Comment("맥주 Id")
    @Column(nullable = false)
    private Integer beerId;

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
    private Review(Integer previousId, Integer beerId, String memberId, Route route, FeelStatus feelStatus, ReviewFlavorIds flavorIds, String content, String imageUrl, Boolean isPublic) {
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
