package com.beerair.core.beer.application;

import com.beerair.core.beer.domain.vo.rs.BeerResponse;
import com.beerair.core.beer.infrastructure.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeerService {
    private final BeerRepository beerRepository;

	public BeerResponse get(Long beerId) {
		var memberId = getLoginMemberId();

		if (memberId == null) {
			return BeerResponse.from(beerRepository.findByIdWithTypeAndCountry(beerId));
		}

		return BeerResponse.from(beerRepository.findByIdWithTypeAndCountry(beerId, memberId));
	}

    public Boolean existsByKorNameOrEngName(String name) {
        return beerRepository.findByKorNameOrEngName(name, name);
    }

	// TODO: 로그인 구현 전 임시
	private Long getLoginMemberId() {
		return 1L;
	}
}
