package com.beerair.core.acceptance.datasetup;

import com.beerair.core.fixture.Fixture;
import com.beerair.core.region.domain.Country;
import com.beerair.core.region.infrastructure.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CountryDataSetup extends DataSetup {
    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    @Override
    protected void execute() {
        Country country1 = Country.builder()
                .backgroundImageUrl("https://beerair-service.s3.ap-northeast-2.amazonaws.com/COUNTRY/background/korea.png")
                .imageUrl("https://beerair-service.s3.ap-northeast-2.amazonaws.com/COUNTRY/korea.png")
                .engName("korea")
                .korName("대한민국")
                .continentId(1L)
                .build();
        new Fixture<>(country1).set("id", 1L);

        Country country2 = Country.builder()
                .backgroundImageUrl("https://beerair-service.s3.ap-northeast-2.amazonaws.com/COUNTRY/background/denmark.png")
                .imageUrl("https://beerair-service.s3.ap-northeast-2.amazonaws.com/COUNTRY/denmark.png")
                .engName("denmark")
                .korName("덴마크")
                .continentId(2L)
                .build();
        new Fixture<>(country2).set("id", 2L);

        Country country3 = Country.builder()
                .backgroundImageUrl("https://beerair-service.s3.ap-northeast-2.amazonaws.com/COUNTRY/background/usa.png")
                .imageUrl("https://beerair-service.s3.ap-northeast-2.amazonaws.com/COUNTRY/usa.png")
                .engName("usa")
                .korName("미국")
                .continentId(3L)
                .build();
        new Fixture<>(country2).set("id", 3L);

        countryRepository.saveAll(
                List.of(country1, country2, country3)
        );
    }
}
