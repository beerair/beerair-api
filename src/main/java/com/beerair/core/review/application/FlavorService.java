package com.beerair.core.review.application;

import com.beerair.core.review.dto.response.FlavorResponse;
import com.beerair.core.review.infrastructure.FlavorRankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class FlavorService {
    private final FlavorRankRepository flavorRankRepository;

    @Transactional(readOnly = true)
    public List<FlavorResponse> getFlavorTop3(String beerId) {
        return flavorRankRepository
                .findTop3ByBeerId(beerId)
                .stream()
                .map(each -> new FlavorResponse(each.getId(), each.getContent()))
                .collect(Collectors.toList());
    }
}
