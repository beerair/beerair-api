package com.beerair.core.review.infrastructure;

import com.beerair.core.review.dto.query.FlavorDto;

import java.util.List;

public interface FlavorRankRepository {
    List<FlavorDto> findTop3ByBeerId(String beerId);
}
