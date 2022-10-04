package com.beerair.core.review.infrastructure;

import com.beerair.core.review.dto.query.FlavorRankDto;
import java.util.List;

public interface FlavorRankRepository {
    List<FlavorRankDto> findRankByBeerId(String beerId, int limit);
}
