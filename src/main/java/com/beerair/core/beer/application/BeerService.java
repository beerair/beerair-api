package com.beerair.core.beer.application;

import com.beerair.core.beer.dto.request.BeerSearchRequest;
import com.beerair.core.beer.dto.response.BeerResponse;
import com.beerair.core.beer.dto.response.BeerStatisticsResponse;
import com.beerair.core.beer.infrastructure.BeerRecommendRepository;
import com.beerair.core.beer.infrastructure.BeerRepository;
import com.beerair.core.beer.infrastructure.BeerSearchRepository;
import com.beerair.core.error.exception.beer.BeerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    public Page<BeerResponse> search(String memberId, BeerSearchRequest request) {
        var searched = beerSearchRepository.search(
                memberId,
                request.toBeerSearchCondition(),
                request.getOrder(),
                request.getOffset(),
                SEARCH_LIMIT
        );
        return searched.map(BeerResponse::ofListItem);
    }

    public List<BeerResponse> getRecommends(String memberId) {
        return beerRecommendRepository.findRecommends(memberId, RECOMMEND_LIMIT)
                .stream()
                .map(BeerResponse::ofListItem)
                .collect(Collectors.toList());
    }

    /**
     * Beer 통계 자료 제공 API
     */
    public BeerStatisticsResponse getStatistics(String memberId) {
        /** 추후 다른 용도로 사용하기 위해 전체 리스트 조회 - 로컬 캐시 저장 적용 필요 */
        var beers = beerRepository.findAll();
        var totalBeerCount = (long) beers.size();
        var isActiveBeerCount = beers.stream()
                .filter(beer -> !beer.isDeleted())
                .count();
        var isDeletedBeerCount = totalBeerCount - isActiveBeerCount;

        return new BeerStatisticsResponse(totalBeerCount, isActiveBeerCount, isDeletedBeerCount);
    }
}
