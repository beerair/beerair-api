package com.beerair.core.region.application;

import com.beerair.core.region.dto.response.CountryResponse;
import com.beerair.core.region.infrastructure.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public List<CountryResponse> getAll() {
        return countryRepository.findAll()
                .stream().map(CountryResponse::from)
                .collect(Collectors.toList());
    }

    public List<CountryResponse> getByContinentId(Long continentId) {
        return countryRepository.findByContinentId(continentId)
                .stream()
                .map(CountryResponse::from)
                .collect(Collectors.toList());
    }
}
