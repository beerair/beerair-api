package com.beerair.core.unit.review.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;

import com.beerair.core.review.domain.vo.Route;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RouteTest {
    private static final Long FIRST_COUNTRY = 1L;
    private static final Long CURRENT_COUNTRY = 2L;
    private static final Long NEXT_COUNTRY = 3L;

    @DisplayName("출발지의 Dummy Route")
    @Test
    void ofOnlyArrival() {
        var route = Route.ofOnlyArrival(FIRST_COUNTRY);

        assertThat(route.getDepartureCountryId())
                .isNull();
        assertThat(route.getArrivalCountryId())
                .isEqualTo(1);
    }

    @DisplayName("이전 경로를 참고해 이번 경로를 구한다.")
    @Test
    void next() {
        var defaultRoute = Route.ofOnlyArrival(FIRST_COUNTRY);
        var currentRoute = Route.next(defaultRoute, CURRENT_COUNTRY);

        assertThat(currentRoute.getDepartureCountryId())
                .isEqualTo(FIRST_COUNTRY);
        assertThat(currentRoute.getArrivalCountryId())
                .isEqualTo(CURRENT_COUNTRY);
    }

    @DisplayName("[Route 삭제에 해당] 현재 경로를 없애고 이전 경로와 다음 경로를 이어 붙인다..")
    @Test
    void join() {
        var defaultRoute = Route.ofOnlyArrival(FIRST_COUNTRY);
        var currentRoute = Route.next(defaultRoute, CURRENT_COUNTRY);
        var nextRoute = Route.next(currentRoute, NEXT_COUNTRY);

        var newRoute = currentRoute.join(nextRoute);

        assertThat(newRoute.getDepartureCountryId()).isEqualTo(FIRST_COUNTRY);
        assertThat(newRoute.getArrivalCountryId()).isEqualTo(NEXT_COUNTRY);
    }
}
