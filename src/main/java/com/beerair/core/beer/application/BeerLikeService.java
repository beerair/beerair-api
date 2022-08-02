package com.beerair.core.beer.application;

import com.beerair.core.beer.infrastructure.BeerLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeerLikeService {
    private final BeerLikeRepository beerLikeRepository;
}
