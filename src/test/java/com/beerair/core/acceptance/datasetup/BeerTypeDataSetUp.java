package com.beerair.core.acceptance.datasetup;

import com.beerair.core.beer.domain.BeerType;
import com.beerair.core.beer.infrastructure.BeerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BeerTypeDataSetUp extends DataSetup {
    @Autowired
    private BeerTypeRepository beerTypeRepository;

    @Transactional
    @Override
    protected void execute() {
        BeerType beerType = BeerType.builder()
                .engName("ALE")
                .korName("에일")
                .content("쓴 맛이 적고 단 맛이 나며, 풀 바디감이 느껴지는 목 넘김이 깔끔한 맥주")
                .imageUrl("https://beerair-service.s3.ap-northeast-2.amazonaws.com/BEER/TYPE/ale.png")
                .build();
        beerTypeRepository.save(beerType);
    }
}
