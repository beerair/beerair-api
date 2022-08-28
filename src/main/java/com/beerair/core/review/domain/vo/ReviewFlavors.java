package com.beerair.core.review.domain.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.List;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewFlavors {
    @Comment("맥주 맛[1] Id")
    @Column
    private Long flavor1;

    @Comment("맥주 맛[2] Id")
    private Long flavor2;

    @Comment("맥주 맛[3] Id")
    private Long flavor3;

    private ReviewFlavors(Long flavor1, Long flavor2, Long flavor3) {
        this.flavor1 = flavor1;
        this.flavor2 = flavor2;
        this.flavor3 = flavor3;
    }

    public static ReviewFlavors from(List<Long> values) {
        return new ReviewFlavors(
                values.size() > 0 ? values.get(0) : null,
                values.size() > 1 ? values.get(1) : null,
                values.size() > 2 ? values.get(2) : null
        );
    }
}
