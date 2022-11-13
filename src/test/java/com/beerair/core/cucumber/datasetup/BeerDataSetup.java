package com.beerair.core.cucumber.datasetup;

import com.beerair.core.beer.domain.Beer;
import com.beerair.core.beer.infrastructure.BeerRepository;
import com.beerair.core.fixture.Fixture;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BeerDataSetup extends DataSetup {
    @Autowired
    private BeerRepository beerRepository;

    @Transactional
    @Override
    protected void execute() {
        Beer beer1 = Beer.builder()
                .alcohol(4.1f)
                .korName("제주 슬라이스")
                .engName("Jeju Slice")
                .imageUrl("https://beerair-service.s3.ap-northeast-2.amazonaws.com/BEER/jeju_slice.png")
                .countryId(1L)
                .typeId(1L)
                .build();

        Beer beer2 = Beer.builder()
                .alcohol(5.2f)
                .korName("에일의 정석")
                .engName("Standard Of Ale")
                .imageUrl("https://beerair-service.s3.ap-northeast-2.amazonaws.com/BEER/standard_of_ale.png")
                .countryId(2L)
                .typeId(2L)
                .build();

        Beer beer3 = Beer.builder()
                .alcohol(9.9f)
                .korName("미국 맥주")
                .engName("USA USA USA")
                .imageUrl("https://beerair-service.s3.ap-northeast-2.amazonaws.com/BEER/usa_usa_usa.png")
                .countryId(3L)
                .typeId(3L)
                .build();

        beerRepository.saveAll(
                List.of(beer1, beer2, beer3)
        );
    }
}
