package com.beerair.core.suggest.facade;

import com.beerair.core.beer.application.BeerService;
import com.beerair.core.common.util.MapperUtil;
import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.suggest.BeerAlreadyExistsException;
import com.beerair.core.error.exception.suggest.BeerSuggestAlreadyExistsException;
import com.beerair.core.suggest.application.BeerSuggestService;
import com.beerair.core.suggest.domain.BeerSuggest;
import com.beerair.core.suggest.dto.request.BeerSuggestRegisterRequest;
import com.beerair.core.suggest.dto.response.BeerSuggestRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeerSuggestFacade {
    private final BeerSuggestService beerSuggestService;
    private final BeerService beerService;

    public void validate(String name, Long memberId) {
        if (beerService.existsByKorNameOrEngName(name)) {
            throw new BeerAlreadyExistsException();
        }
        if (beerSuggestService.existsByNameAndMemberId(name, memberId)) {
            throw new BeerSuggestAlreadyExistsException();
        }
    }

    public BeerSuggestRegisterResponse register(Long memberId, BeerSuggestRegisterRequest request) {
        validate(request.getName(), memberId);

        var suggest = beerSuggestService.save(
                new BeerSuggest(
                        MapperUtil.writeValueAsString(request.getImages()),
                        request.getName(),
                        memberId
                )
        );

        // TODO : SLACK MONITORING

        return new BeerSuggestRegisterResponse(suggest.getId(), suggest.getBeerName());
    }
}
