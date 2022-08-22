package com.beerair.core.beer.application;

import com.beerair.core.beer.dto.response.BeerResponse;
import com.beerair.core.beer.infrastructure.BeerRepository;
import com.beerair.core.error.exception.beer.BeerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeerService {
    private final BeerRepository beerRepository;

    public BeerResponse get(Long beerId) {
        return BeerResponse.from(beerRepository.findById(beerId)
                .orElseThrow(BeerNotFoundException::new));
    }

    public BeerResponse getWithRegion(Long beerId) {
        var memberId = getLoginMemberId();

        if (memberId == null) {
            return BeerResponse.from(beerRepository.findByIdWithTypeAndCountry(beerId)
                    .orElseThrow(BeerNotFoundException::new));
        }

        return BeerResponse.from(beerRepository.findByIdWithTypeAndCountry(beerId, memberId)
                .orElseThrow(BeerNotFoundException::new));
    }

    public Boolean existsByKorNameOrEngName(String name) {
        return beerRepository.findByKorNameOrEngName(name, name);
    }

    // TODO: 로그인 구현 전 임시
    private String getLoginMemberId() {
        return "";
    }
}
