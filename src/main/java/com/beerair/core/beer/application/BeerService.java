package com.beerair.core.beer.application;

import com.beerair.core.beer.dto.response.BeerResponse;
import com.beerair.core.beer.infrastructure.BeerRepository;
import com.beerair.core.error.exception.beer.BeerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BeerService {
    private final BeerRepository beerRepository;

    public BeerResponse getWithRegion(String memberId, Long beerId) {
        if (Objects.isNull(memberId)) {
            return BeerResponse.from(beerRepository.findByIdWithTypeAndCountry(beerId)
                    .orElseThrow(BeerNotFoundException::new));
        }
        return BeerResponse.from(beerRepository.findByIdWithTypeAndCountry(beerId, memberId)
                .orElseThrow(BeerNotFoundException::new));
    }

    public BeerResponse getWithRegion(Long beerId) {
        return getWithRegion(null, beerId);
    }

    public Boolean existsByKorNameOrEngName(String name) {
        return beerRepository.findByKorNameOrEngName(name, name);
    }
}
