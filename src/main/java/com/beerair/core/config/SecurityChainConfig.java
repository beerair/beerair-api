package com.beerair.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.beerair.core.auth.domain.AuthTokenProvider;
import com.beerair.core.auth.domain.OAuth2AttributesLoader;
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
        @Value("${jjwt.signatureAlgorithm}") String signatureAlgorithm,
        @Value("${jjwt.signatureKey}") String signatureKey,
        @Value("${jjwt.expiration}") int expiration) {
        return new OAuth2JJwtProvider(signatureAlgorithm, signatureKey, expiration);
    }
}
