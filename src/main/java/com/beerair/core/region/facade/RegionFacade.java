package com.beerair.core.region.facade;

import com.beerair.core.error.exception.region.ContinentNotFoundException;
import com.beerair.core.region.application.ContinentService;
import com.beerair.core.region.application.CountryService;
import com.beerair.core.region.dto.response.ContinentResponse;
import com.beerair.core.region.dto.response.CountryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionFacade {
    private final ContinentService continentService;
    private final CountryService countryService;

    public List<ContinentResponse> getAllContinents() {
        return continentService.getAll();
    }

    public List<CountryResponse> getAllCountries() {
        return countryService.getAll();
    }

    public List<CountryResponse> getCountriesByContinentId(Long continentId) {
        validateContinent(continentId);

        return countryService.getByContinentId(continentId);
    }

    private void validateContinent(Long continentId) {
        if (!continentService.exists(continentId)) {
            throw new ContinentNotFoundException();
        }
    }
}
