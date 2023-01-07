package com.beerair.core.beer.application;

import com.beerair.core.beer.domain.BeerLike;
import com.beerair.core.beer.dto.response.BeerResponse;
import com.beerair.core.beer.infrastructure.BeerLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class BeerLikeService {
    private final BeerLikeRepository beerLikeRepository;

    public void like(String memberId, Integer beerId) {
        var beerLike = new BeerLike(memberId, beerId);
        beerLikeRepository.save(beerLike);
    }

    public void unlike(String memberId, Integer beerId) {
        beerLikeRepository.deleteByBeerIdAndMemberId(memberId, beerId);
    }

    public int getCount(String memberId) {
        return beerLikeRepository.findCountByMemberId(memberId);
    }

    public List<BeerResponse> getAll(String memberId) {
        return beerLikeRepository.findAllByMemberIdWithBeer(memberId)
                .stream()
                .map(BeerResponse::ofListItem)
                .collect(Collectors.toList());
    }
}
