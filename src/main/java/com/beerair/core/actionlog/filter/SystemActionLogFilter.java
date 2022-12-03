package com.beerair.core.actionlog.filter;

import com.beerair.core.actionlog.event.publish.SystemActionLogEventPublish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SystemActionLogFilter implements Filter {
    private final SystemActionLogEventPublish systemActionLogEventPublish;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        var httpServletRequest = (HttpServletRequest) request;

        systemActionLogEventPublish.publish(httpServletRequest);

        chain.doFilter(request, response);
    }
}
