package com.beerair.core.config.web;

import java.util.List;

import com.beerair.core.auth.presentation.AuthMemberIdArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.beerair.core.auth.presentation.AuthMemberArgumentResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(memberArgumentResolver());
        resolvers.add(memberIdArgumentResolver());
    }

    @Bean
    public AuthMemberArgumentResolver memberArgumentResolver() {
        return new AuthMemberArgumentResolver();
    }

    @Bean
    public AuthMemberIdArgumentResolver memberIdArgumentResolver() {
        return new AuthMemberIdArgumentResolver();
    }
}
