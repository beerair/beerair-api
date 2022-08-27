package com.beerair.core.acceptance.datasetup;

import com.beerair.core.beer.domain.BeerType;
import com.beerair.core.beer.infrastructure.BeerTypeRepository;
import com.beerair.core.fixture.Fixture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BeerTypeDataSetUp extends DataSetup {
    @Autowired
    private BeerTypeRepository beerTypeRepository;

    @Transactional
    @Override
    protected void execute() {
        BeerType beerType1 = BeerType.builder()
                .engName("ALE")
                .korName("에일")
                .content("쓴 맛이 적고 단 맛이 나며, 풀 바디감이 느껴지는 목 넘김이 깔끔한 맥주")
                .imageUrl("https://beerair-service.s3.ap-northeast-2.amazonaws.com/BEER/TYPE/ale.png")
                .build();
        new Fixture<>(beerType1).set("id", 1L);

        BeerType beerType2 = BeerType.builder()
                .engName("PALE_ALE")
                .korName("페일 에일")
                .content("붉은 빛과 맑고 밝은 빛을 띄며, 꽃향기와 같은 풍부한 향이 느껴지는 맥주")
                .imageUrl("https://beerair-service.s3.ap-northeast-2.amazonaws.com/BEER/TYPE/pale_ale.png")
                .build();
        new Fixture<>(beerType2).set("id", 2L);

        beerTypeRepository.saveAll(
                List.of(beerType1, beerType2)
        );
    }
}
