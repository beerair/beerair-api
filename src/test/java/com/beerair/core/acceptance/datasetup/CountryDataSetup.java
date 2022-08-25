package com.beerair.core.acceptance.datasetup;

import com.beerair.core.region.domain.Country;
import com.beerair.core.region.infrastructure.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CountryDataSetup extends DataSetup {
    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    @Override
    protected void execute() {
        Country country = Country.builder()
                .backgroundImageUrl("https://beerair-service.s3.ap-northeast-2.amazonaws.com/COUNTRY/background/korea.png")
                .imageUrl("https://beerair-service.s3.ap-northeast-2.amazonaws.com/COUNTRY/korea.png")
                .engName("korea")
                .korName("대한민국")
                .continentId(1L)
                .build();
        countryRepository.save(country);
    }
}
