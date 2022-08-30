package com.beerair.core.review.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RouteTest {
    @DisplayName("출발지의 Dummy Route")
    @Test
    void ofOnlyArrival() {
        Long COUNTRY = 1L;

        var route = Route.ofOnlyArrival(COUNTRY);
        assertThat(route.getDepartureCountryId())
                .isNull();
        assertThat(route.getArrivalCountryId())
                .isEqualTo(1);
    }

    @DisplayName("이전 경로를 참고해 이번 경로를 구한다.")
    @Test
    void next() {
        Long FIRST_COUNTRY = 1L;
        Long CURRENT_COUNTRY = 2L;

        var defaultRoute = Route.ofOnlyArrival(FIRST_COUNTRY);
        var currentRoute = Route.next(defaultRoute, CURRENT_COUNTRY);
        assertThat(currentRoute.getDepartureCountryId())
                .isEqualTo(FIRST_COUNTRY);
        assertThat(currentRoute.getArrivalCountryId())
                .isEqualTo(CURRENT_COUNTRY);
    }
}
