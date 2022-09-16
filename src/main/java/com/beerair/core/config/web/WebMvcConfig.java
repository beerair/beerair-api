package com.beerair.core.config.web;

import com.beerair.core.auth.presentation.aop.AuthMemberArgumentResolver;
import com.beerair.core.auth.presentation.filter.GetAuthenticationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final GetAuthenticationStrategy getAuthenticationStrategy;

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(memberArgumentResolver());
    }

    @Bean
    public AuthMemberArgumentResolver memberArgumentResolver() {
        return new AuthMemberArgumentResolver(getAuthenticationStrategy);
    }
}
