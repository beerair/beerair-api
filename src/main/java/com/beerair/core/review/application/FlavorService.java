package com.beerair.core.review.application;

import com.beerair.core.review.infrastructure.FlavorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlavorService {
    private final FlavorRepository flavorRepository;
}
