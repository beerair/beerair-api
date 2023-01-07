package com.beerair.core.beer.application;

import com.beerair.core.beer.dto.request.BeerSearchRequest;
import com.beerair.core.beer.dto.response.BeerResponse;
import com.beerair.core.beer.dto.response.BeerSearchResponse;
import com.beerair.core.beer.infrastructure.BeerRecommendRepository;
import com.beerair.core.beer.infrastructure.BeerRepository;
import com.beerair.core.beer.infrastructure.BeerSearchRepository;
import com.beerair.core.error.exception.beer.BeerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeerService {
    private static final int RECOMMEND_LIMIT = 9;
    public static final int SEARCH_LIMIT = 30;

    private final BeerRepository beerRepository;
    private final BeerSearchRepository beerSearchRepository;
    private final BeerRecommendRepository beerRecommendRepository;

    public BeerResponse getWithRegion(String memberId, Integer beerId) {
        if (Objects.isNull(memberId)) {
            return BeerResponse.from(beerRepository.findByIdWithTypeAndCountry(beerId)
                    .orElseThrow(BeerNotFoundException::new));
        }
        return BeerResponse.from(beerRepository.findByIdWithTypeAndCountry(beerId, memberId)
                .orElseThrow(BeerNotFoundException::new));
    }

    public Boolean existsByKorNameOrEngName(String name) {
        return beerRepository.findByKorNameOrEngName(name, name);
    }

    public BeerSearchResponse search(String memberId, BeerSearchRequest request) {
        var total = beerRepository.findCount();

        var searched = beerSearchRepository.search(
                memberId,
                request.toBeerSearchCondition(),
                request.getOrder(),
                request.getOffset(),
                SEARCH_LIMIT
        );

        var contents = searched.stream()
                .map(BeerResponse::ofListItem)
                .collect(Collectors.toList());

        return BeerSearchResponse.from(contents, total);
    }

    public List<BeerResponse> getRecommends(String memberId) {
        return beerRecommendRepository.findRecommends(memberId, RECOMMEND_LIMIT)
                .stream()
                .map(BeerResponse::ofListItem)
                .collect(Collectors.toList());
    }
}
