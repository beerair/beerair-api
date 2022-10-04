package com.beerair.core.beer.infrastructure;

import com.beerair.core.beer.dto.query.BeerListItemDto;
import com.beerair.core.beer.infrastructure.search.BeerOrderBy;
import com.beerair.core.beer.infrastructure.search.BeerSearchCondition;
import java.util.List;

public interface BeerSearchRepository {
    List<BeerListItemDto> search(String memberId, BeerSearchCondition where, BeerOrderBy order, int offset, int limit);
}
