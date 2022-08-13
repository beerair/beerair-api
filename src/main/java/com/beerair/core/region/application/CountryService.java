package com.beerair.core.region.application;

import com.beerair.core.region.domain.vo.rs.CountryResponse;
import com.beerair.core.region.domain.vo.rs.CountryResponses;
import com.beerair.core.region.infrastructure.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryResponses getByContinentId(Long continentId) {
        return CountryResponses.from(
                countryRepository.findByContinentId(continentId)
                .stream()
                .map(CountryResponse::from)
                .collect(Collectors.toList()));
    }
}
