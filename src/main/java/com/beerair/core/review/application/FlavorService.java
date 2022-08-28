package com.beerair.core.review.application;

import com.beerair.core.review.dto.response.FlavorResponse;
import com.beerair.core.review.infrastructure.FlavorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlavorService {
    private final FlavorRepository flavorRepository;

    public List<FlavorResponse> getFlavorTop3(String beerId) {
        return Collections.emptyList();
    }
}
