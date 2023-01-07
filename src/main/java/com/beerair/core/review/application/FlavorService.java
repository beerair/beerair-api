package com.beerair.core.review.application;

import com.beerair.core.review.dto.response.FlavorRankResponse;
import com.beerair.core.review.dto.response.FlavorResponse;
import com.beerair.core.review.infrastructure.FlavorRankRepository;
import com.beerair.core.review.infrastructure.FlavorRepository;
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
    private final FlavorRepository flavorRepository;

    public List<FlavorResponse> getAll() {
        return flavorRepository.findAll()
                .stream()
                .map(flavor -> new FlavorResponse(flavor.getId(), flavor.getContent()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FlavorRankResponse> getFlavorRank(Integer beerId, int limit) {
        return flavorRankRepository.findRankByBeerId(beerId, limit)
                .stream()
                .map(FlavorRankResponse::from)
                .collect(Collectors.toList());
    }
}
