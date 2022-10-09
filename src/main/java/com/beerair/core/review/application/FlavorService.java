package com.beerair.core.review.application;

import com.beerair.core.review.dto.response.FlavorRankResponse;
import com.beerair.core.review.dto.response.FlavorResponse;
import com.beerair.core.review.dto.response.FlavorsResponse;
import com.beerair.core.review.infrastructure.FlavorRankRepository;
import com.beerair.core.review.infrastructure.FlavorRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class FlavorService {
    private final FlavorRankRepository flavorRankRepository;
    private final FlavorRepository flavorRepository;

    public FlavorsResponse getAll() {
        var flavors = flavorRepository.findAll()
                .stream()
                .map(flavor -> new FlavorResponse(flavor.getId(), flavor.getContent()))
                .collect(Collectors.toList());

        return new FlavorsResponse(flavors);
    }

    @Transactional(readOnly = true)
    public List<FlavorRankResponse> getFlavorRank(String beerId, int limit) {
        return flavorRankRepository
                .findRankByBeerId(beerId, limit)
                .stream()
                .map(FlavorRankResponse::from)
                .collect(Collectors.toList());
    }
}
