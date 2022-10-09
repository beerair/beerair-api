package com.beerair.core.beer.infrastructure.search;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BeerSearchCondition {
    private final String keyword;
    private final List<Long> countries;
    private final List<Long> types;

    @Builder
    private BeerSearchCondition(String keyword, List<Long> countries, List<Long> types) {
        this.keyword = keyword;
        this.countries = countries;
        this.types = types;
    }
}
