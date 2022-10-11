package com.beerair.core.cruiser.domain;

import com.beerair.core.cruiser.dto.slack.CruiserRequest;

public interface CruiserClient {
    /**
     * Cruiser For Message
     *
     * @param request 알림 메세지
     * @apiNote 알림 서비스를 제공하는 최상위 인터페이스
     **/
    void send(CruiserRequest request);
}
