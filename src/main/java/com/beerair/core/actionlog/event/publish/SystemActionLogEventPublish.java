package com.beerair.core.actionlog.event.publish;

import com.beerair.core.actionlog.event.model.SystemActionLogEventModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class SystemActionLogEventPublish {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(HttpServletRequest request) {
        if (request.getRequestURI().startsWith("/api")) {
            var event = new SystemActionLogEventModel(request);
            applicationEventPublisher.publishEvent(event);
        }
    }
}
