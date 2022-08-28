package com.beerair.core.beer.infrastructure;

import com.beerair.core.beer.dto.query.BeerListItemDto;

import java.util.List;

public interface BeerRecommendRepository {
    List<BeerListItemDto> findRecommends(String memberId, int limit);
}
