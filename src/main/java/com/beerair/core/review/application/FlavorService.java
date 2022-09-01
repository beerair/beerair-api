package com.beerair.core.review.application;

import com.beerair.core.review.dto.response.FlavorResponse;
import com.beerair.core.review.dto.response.FlavorsResponse;
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

    public FlavorsResponse getAll() {
        var flavors = flavorRepository.findAll()
                .stream()
                .map(flavor -> new FlavorResponse(flavor.getId(), flavor.getContent()))
                .collect(Collectors.toList());

        return new FlavorsResponse(flavors);
    }

    @Transactional(readOnly = true)
    public List<FlavorResponse> getFlavorTop3(String beerId) {
        return flavorRankRepository
                .findTop3ByBeerId(beerId)
                .stream()
                .map(each -> new FlavorResponse(each.getId(), each.getContent()))
                .collect(Collectors.toList());
    }
}
