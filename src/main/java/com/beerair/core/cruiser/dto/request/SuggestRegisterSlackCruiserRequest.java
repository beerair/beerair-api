package com.beerair.core.cruiser.dto.request;

import com.beerair.core.cruiser.dto.slack.CruiserRequest;
import com.beerair.core.cruiser.dto.slack.SlackCruiserMessage;
import com.beerair.core.suggest.domain.Suggest;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SuggestRegisterSlackCruiserRequest implements SlackCruiserMessage {
    private final Suggest suggest;

    @Override
    public CruiserRequest message() {
        return new CruiserRequest(
                "*:beerair: BeerAir 맥주 제안 :beerair:*\n" +
                        "id : " + suggest.getId() + "\n" +
                        "name : " + suggest.getBeerName() + "\n" +
                        "imageUrls : " + suggest.getImageUrls() + "\n" +
                        "memberId : " + suggest.getMemberId()
        );
    }
}
