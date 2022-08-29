package com.beerair.core.suggest.application;

import com.beerair.core.suggest.domain.Suggest;
import com.beerair.core.suggest.dto.response.SuggestCountResponse;
import com.beerair.core.suggest.dto.response.SuggestResponse;
import com.beerair.core.suggest.infrastructure.SuggestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuggestService {
    private final SuggestRepository suggestRepository;

    public Boolean existsByNameAndMemberId(String name, String memberId) {
        return suggestRepository.existsByBeerNameAndMemberId(name, memberId);
    }

    public Suggest save(Suggest suggest) {
        return suggestRepository.save(suggest);
    }

    public SuggestCountResponse count(String memberId) {
        var count = suggestRepository.countByMemberId(memberId);

        return new SuggestCountResponse(memberId, count);
    }

    public Page<SuggestResponse> getAll(String memberId, Pageable pageable) {
        return suggestRepository.findAllByMemberId(pageable, memberId)
                .map(SuggestResponse::new);
    }
}
