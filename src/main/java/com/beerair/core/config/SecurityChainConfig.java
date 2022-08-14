package com.beerair.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.beerair.core.auth.application.AuthTokenProvider;
import com.beerair.core.auth.infrastructure.oauth2.OAuth2AttributesLoader;
import com.beerair.core.auth.infrastructure.jwt.OAuth2JJwtProvider;
import com.beerair.core.auth.infrastructure.oauth2.NaverOAuth2AttributesLoader;

@Configuration
public class SecurityChainConfig {
    @Bean
    public OAuth2AttributesLoader oAuth2AttributesLoader() {
        return new NaverOAuth2AttributesLoader();
    }

    @Bean
    public AuthTokenProvider jwtProvider(
        @Value("${auth.jjwt.signatureAlgorithm}") String signatureAlgorithm,
        @Value("${auth.jjwt.signatureKey}") String signatureKey,
        @Value("${auth.jjwt.expiration}") int expiration) {
        return new OAuth2JJwtProvider(signatureAlgorithm, signatureKey, expiration);
    }
}
