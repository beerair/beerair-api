package com.beerair.core.suggest.application;

import com.beerair.core.suggest.domain.BeerSuggest;
import com.beerair.core.suggest.infrastructure.BeerSuggestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeerSuggestService {
    private final BeerSuggestRepository beerSuggestRepository;

    public Boolean existsByNameAndMemberId(String name, Long memberId) {
        return beerSuggestRepository.existsByBeerNameAndMemberId(name, memberId);
    }

    public BeerSuggest save(BeerSuggest beerSuggest) {
        return beerSuggestRepository.save(beerSuggest);
    }
}
