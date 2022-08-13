package com.beerair.core.beer.application;

import com.beerair.core.beer.domain.vo.rs.BeerTypeResponse;
import com.beerair.core.beer.domain.vo.rs.BeerTypeResponses;
import com.beerair.core.beer.infrastructure.BeerTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

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
