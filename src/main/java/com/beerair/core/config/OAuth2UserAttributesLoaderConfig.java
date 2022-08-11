package com.beerair.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.beerair.core.auth.application.OAuth2AttributesLoader;
import com.beerair.core.auth.infrastructure.NaverOAuth2AttributesLoader;

@Configuration
public class OAuth2UserAttributesLoaderConfig {
    @Bean
    public OAuth2AttributesLoader oAuth2AttributesLoader() {
        return new NaverOAuth2AttributesLoader();
    }
}
