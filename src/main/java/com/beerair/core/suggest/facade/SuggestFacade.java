package com.beerair.core.suggest.facade;

import com.beerair.core.beer.application.BeerService;
import com.beerair.core.common.util.MapperUtil;
import com.beerair.core.error.exception.suggest.BeerAlreadyExistsException;
import com.beerair.core.error.exception.suggest.BeerSuggestAlreadyExistsException;
import com.beerair.core.suggest.application.SuggestService;
import com.beerair.core.suggest.domain.Suggest;
import com.beerair.core.suggest.dto.request.SuggestRegisterRequest;
import com.beerair.core.suggest.dto.response.SuggestRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuggestFacade {
    private final SuggestService suggestService;
    private final BeerService beerService;

    public void validate(String memberId, String name) {
        if (beerService.existsByKorNameOrEngName(name)) {
            throw new BeerAlreadyExistsException();
        }
        if (suggestService.existsByNameAndMemberId(name, memberId)) {
            throw new BeerSuggestAlreadyExistsException();
        }
    }

    public SuggestRegisterResponse register(String memberId, SuggestRegisterRequest request) {
        validate(request.getName(), memberId);

        var suggest = suggestService.save(
                new Suggest(
                        request.getName(),
                        MapperUtil.writeValueAsString(request.getImages()),
                        memberId
                )
        );

        // TODO : SLACK MONITORING

        return new SuggestRegisterResponse(suggest.getId(), suggest.getBeerName());
    }
}
