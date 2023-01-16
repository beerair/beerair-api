package com.beerair.core.cruiser.dto.request;

import com.beerair.core.cruiser.dto.slack.CruiserRequest;
import com.beerair.core.cruiser.dto.slack.SlackCruiserMessage;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StaticsSlackCruiserRequest implements SlackCruiserMessage {
    private final Long memberCount;
    private final Long totalApiCallCount;

    @Override
    public CruiserRequest message() {
        var message = "유저수 " + memberCount + " / 총 API 요청 수 " + totalApiCallCount;
        return new CruiserRequest(message);
    }
}
