package com.beerair.core.review.domain.vo;

import lombok.Getter;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Route {
    @Comment("출발 국가 ID")
    @Column(nullable = false)
    private Long departureCountryId;

    @Comment("도착 국가 ID")
    @Column(nullable = false)
    private Long arrivalCountryId;

    protected Route() {
    }

    private Route(Long departureCountryId, Long arrivalCountryId) {
        this.departureCountryId = departureCountryId;
        this.arrivalCountryId = arrivalCountryId;
    }

    public static Route ofOnlyArrival(Long arrivalCountryId) {
        return new Route(null, arrivalCountryId);
    }

    public static Route next(Route previous, Long arrivalCountryId) {
        return new Route(previous.getArrivalCountryId(), arrivalCountryId);
    }
}
