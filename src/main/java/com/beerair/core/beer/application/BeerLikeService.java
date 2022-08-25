package com.beerair.core.beer.application;

import com.beerair.core.beer.domain.BeerLike;
import com.beerair.core.beer.dto.response.BeerResponse;
import com.beerair.core.beer.infrastructure.BeerLikeRepository;
import com.beerair.core.error.exception.beer.BeerLikeNotFoundException;
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

    public void like(String memberId, String beerId) {
        var like = new BeerLike(memberId, beerId);
        beerLikeRepository.save(like);
    }

    public void unlike(String memberId, String beerId) {
        beerLikeRepository.deleteByBeerIdAndMemberId(memberId, beerId);
    }

    public int getCount(String memberId) {
        return beerLikeRepository.findCountByMemberId(memberId);
    }

    public List<BeerResponse> getAll(String memberId) {
        return beerLikeRepository.findAllByMemberIdAndLiked(memberId)
                .stream()
                .map(BeerResponse::ofListItem)
                .collect(Collectors.toList());
    }
}
