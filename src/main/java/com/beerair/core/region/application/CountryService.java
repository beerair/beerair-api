package com.beerair.core.region.application;

import com.beerair.core.region.dto.response.CountryResponse;
import com.beerair.core.region.dto.response.CountryResponses;
import com.beerair.core.region.infrastructure.CountryRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryResponses getAll() {
        var country = countryRepository.findAll()
                .stream().map(CountryResponse::from)
                .collect(Collectors.toList());

        return new CountryResponses(country);
    }

    public CountryResponses getByContinentId(Long continentId) {
        var country = countryRepository.findByContinentId(continentId)
                .stream()
                .map(CountryResponse::from)
                .collect(Collectors.toList());

        return new CountryResponses(country);
    }
}
