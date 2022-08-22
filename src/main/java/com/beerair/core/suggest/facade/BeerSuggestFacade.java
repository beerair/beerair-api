package com.beerair.core.suggest.facade;

import com.beerair.core.beer.application.BeerService;
import com.beerair.core.common.util.MapperUtil;
import com.beerair.core.error.exception.suggest.BeerAlreadyExistsException;
import com.beerair.core.error.exception.suggest.BeerSuggestAlreadyExistsException;
import com.beerair.core.member.dto.LoggedInUser;
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

    public void validate(String name, LoggedInUser user) {
        if (beerService.existsByKorNameOrEngName(name)) {
            throw new BeerAlreadyExistsException();
        }
        if (beerSuggestService.existsByNameAndMemberId(name, user.getId())) {
            throw new BeerSuggestAlreadyExistsException();
        }
    }

    public BeerSuggestRegisterResponse register(BeerSuggestRegisterRequest request, LoggedInUser user) {
        validate(request.getName(), user);

        var suggest = beerSuggestService.save(
                new BeerSuggest(
                        MapperUtil.writeValueAsString(request.getImages()),
                        request.getName(),
                        user.getId()
                )
        );

        // TODO : SLACK MONITORING

        return new BeerSuggestRegisterResponse(suggest.getId(), suggest.getBeerName());
    }
}
