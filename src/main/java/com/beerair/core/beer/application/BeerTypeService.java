package com.beerair.core.beer.application;

import com.beerair.core.beer.dto.response.BeerTypeResponse;
import com.beerair.core.beer.dto.response.BeerTypeResponses;
import com.beerair.core.beer.infrastructure.BeerTypeRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BeerTypeService {
    private final BeerTypeRepository beerTypeRepository;

    public BeerTypeResponses getAll() {
        return BeerTypeResponses.from(beerTypeRepository.findAll()
                .stream()
                .map(BeerTypeResponse::from)
                .collect(Collectors.toList()));
    }
}
