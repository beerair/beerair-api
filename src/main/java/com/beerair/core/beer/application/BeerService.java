package com.beerair.core.beer.application;

import com.beerair.core.beer.infrastructure.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeerService {
    private final BeerRepository beerRepository;
}
