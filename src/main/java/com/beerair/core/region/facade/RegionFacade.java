package com.beerair.core.region.facade;

import com.beerair.core.region.application.ContinentService;
import com.beerair.core.region.application.CountryService;
import com.beerair.core.region.domain.vo.rs.ContinentResponses;
import com.beerair.core.region.domain.vo.rs.CountryResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionFacade {

    private ContinentService continentService;
    private CountryService countryService;

    public ContinentResponses getAll() {
        return continentService.getAll();
    }

    public CountryResponses getCountriesByContinentId(Long continentId) {
        return countryService.getByContinentId(continentId);
    }
}
