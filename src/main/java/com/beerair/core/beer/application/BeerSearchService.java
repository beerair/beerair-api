package com.beerair.core.beer.application;

import com.beerair.core.beer.dto.query.BeerDto;
import com.beerair.core.beer.dto.request.BeerSearchRequest;
import com.beerair.core.beer.dto.response.BeerResponses;
import com.beerair.core.beer.infrastructure.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BeerSearchService {
    private final BeerRepository beerRepository;

    public BeerResponses search(String memberId, BeerSearchRequest request) {
        List<BeerDto> result = Objects.isNull(memberId) ? searchIfGuest(request) : searchIfMember(memberId, request);
        Long total = beerRepository.findCount();
        assert result != null;

        return null;
    }

    private List<BeerDto> searchIfGuest(BeerSearchRequest request) {
        return beerRepository.findAllByIdWithTypeAndCountryAndOffset(
                request.getOffset(),
                null,
                null
        );
    }

    private List<BeerDto> searchIfMember(String memberId, BeerSearchRequest request) {
        return beerRepository.findAllByIdWithTypeAndCountryAndOffset(
                memberId,
                request.getOffset(),
                null,
                null
        );
    }
}
