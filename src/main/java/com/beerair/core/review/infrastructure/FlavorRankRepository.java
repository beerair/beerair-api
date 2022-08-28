package com.beerair.core.review.infrastructure;

import com.beerair.core.review.dto.query.FlavorDto;

import java.util.List;

public interface FlavorRankRepository {
    List<FlavorDto> getTop3ByBeerId(String beerId);
}
