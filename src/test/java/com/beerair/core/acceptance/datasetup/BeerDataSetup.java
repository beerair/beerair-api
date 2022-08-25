package com.beerair.core.acceptance.datasetup;

import com.beerair.core.beer.domain.Beer;
import com.beerair.core.beer.infrastructure.BeerRepository;
import com.beerair.core.fixture.Fixture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BeerDataSetup extends DataSetup {
    public static final String BEER_ID = "1234";

    @Autowired
    private BeerRepository beerRepository;

    @Transactional
    @Override
    protected void execute() {
        Beer beer = Beer.builder()
                .alcohol(4.1f)
                .korName("제주 슬라이스")
                .engName("Jeju Slice")
                .imageUrl("https://beerair-service.s3.ap-northeast-2.amazonaws.com/BEER/jeju_slice.png")
                .countryId(1L)
                .typeId(1L)
                .build();
        new Fixture<>(beer).set("id", BEER_ID);

        beerRepository.save(beer);
    }
}
