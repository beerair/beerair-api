package com.beerair.core.beer.application;

import com.beerair.core.beer.infrastructure.BeerTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeerTypeService {
    private final BeerTypeRepository beerTypeRepository;
}
