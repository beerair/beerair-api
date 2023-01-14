package com.beerair.core.beer.infrastructure;

import com.beerair.core.beer.dto.query.BeerListItemDto;
import com.beerair.core.beer.infrastructure.search.BeerOrderBy;
import com.beerair.core.beer.infrastructure.search.BeerSearchCondition;
import org.springframework.data.domain.Page;

public interface BeerSearchRepository {
    Page<BeerListItemDto> search(String memberId, BeerSearchCondition where, BeerOrderBy order, int offset, int limit);
}
