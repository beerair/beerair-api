package com.beerair.core.region.facade;

import com.beerair.core.error.exception.region.ContinentNotFoundException;
import com.beerair.core.region.application.ContinentService;
import com.beerair.core.region.application.CountryService;
import com.beerair.core.region.dto.response.ContinentResponses;
import com.beerair.core.region.dto.response.CountryResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionFacade {

    private final ContinentService continentService;
    private final CountryService countryService;

    public ContinentResponses getAllContinents() {
        return continentService.getAll();
    }

    public CountryResponses getAllCountries() {
        return countryService.getAll();
    }

    public CountryResponses getCountriesByContinentId(Long continentId) {

        validateContinent(continentId);

        return countryService.getByContinentId(continentId);
    }

    private void validateContinent(Long continentId) {
        if (!continentService.exists(continentId)) {
            throw new ContinentNotFoundException();
        }
    }
}
