package com.beerair.core.region.application;

import com.beerair.core.region.infrastructure.ContinentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContinentService {
    private final ContinentRepository continentRepository;
}
