package com.beerair.core.suggest.application;

import com.beerair.core.suggest.infrastructure.BeerSuggestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeerSuggestService {
    private final BeerSuggestRepository beerSuggestRepository;
}
