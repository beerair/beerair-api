package com.beerair.core.suggest.application;

import com.beerair.core.member.dto.LoggedInMember;
import com.beerair.core.suggest.domain.BeerSuggest;
import com.beerair.core.suggest.dto.response.BeerSuggestCountResponse;
import com.beerair.core.suggest.dto.response.BeerSuggestResponse;
import com.beerair.core.suggest.infrastructure.BeerSuggestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeerSuggestService {
    private final BeerSuggestRepository beerSuggestRepository;

    public Boolean existsByNameAndMemberId(String name, String memberId) {
        return beerSuggestRepository.existsByBeerNameAndMemberId(name, memberId);
    }

    public BeerSuggest save(BeerSuggest beerSuggest) {
        return beerSuggestRepository.save(beerSuggest);
    }

    public BeerSuggestCountResponse count(LoggedInMember user) {
        var count = beerSuggestRepository.countByMemberId(user.getId());

        return new BeerSuggestCountResponse(user.getId(), count);
    }

    public Page<BeerSuggestResponse> getAll(Pageable pageable, LoggedInMember user) {
        return beerSuggestRepository.findAllByMemberId(pageable, user.getId())
                .map(BeerSuggestResponse::new);
    }
}
