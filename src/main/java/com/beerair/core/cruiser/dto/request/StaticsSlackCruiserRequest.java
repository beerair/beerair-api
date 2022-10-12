package com.beerair.core.cruiser.dto.request;

import com.beerair.core.cruiser.dto.slack.CruiserRequest;
import com.beerair.core.cruiser.dto.slack.SlackCruiserMessage;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StaticsSlackCruiserRequest implements SlackCruiserMessage {
    private final Long memberCount;

    @Override
    public CruiserRequest message() {
        var message = "유저수 " + memberCount;
        return new CruiserRequest(message);
    }
}
