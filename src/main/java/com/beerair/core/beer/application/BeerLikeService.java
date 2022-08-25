package com.beerair.core.beer.application;

import com.beerair.core.beer.domain.Beer;
import com.beerair.core.beer.domain.BeerLike;
import com.beerair.core.beer.infrastructure.BeerLikeRepository;
import com.beerair.core.error.exception.beer.BeerLikeNotFoundException;
import com.beerair.core.member.dto.LoggedInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        var unliked = beerLikeRepository.deleteByBeerIdAndMemberId(memberId, beerId);
        if (unliked < 1) {
            throw new BeerLikeNotFoundException();
        }
    }
}
