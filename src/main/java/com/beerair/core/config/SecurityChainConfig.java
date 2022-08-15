package com.beerair.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.beerair.core.auth.application.AuthTokenProvider;
import com.beerair.core.auth.domain.TokenType;
import com.beerair.core.auth.infrastructure.oauth2.OAuth2AttributesLoader;
import com.beerair.core.auth.infrastructure.jwt.OAuth2JJwtProvider;
import com.beerair.core.auth.infrastructure.oauth2.NaverOAuth2AttributesLoader;

@Configuration
public class SecurityChainConfig {
    @Value("${auth.jjwt.access.signatureAlgorithm}")
    private String accessSignatureAlgorithm;
    @Value("${auth.jjwt.access.signatureKey}")
    private String accessSignatureKey;
    @Value("${auth.jjwt.access.expiration}")
    private int accessExpiration;

    @Value("${auth.jjwt.refresh.signatureAlgorithm}")
    private String refreshSignatureAlgorithm;
    @Value("${auth.jjwt.refresh.signatureKey}")
    private String refreshSignatureKey;
    @Value("${auth.jjwt.refresh.expiration}")
    private int refreshExpiration;


    @Bean
    public OAuth2AttributesLoader oAuth2AttributesLoader() {
        return new NaverOAuth2AttributesLoader();
    }

    @Bean
    public AuthTokenProvider oAuth2AccessProvider() {
        var oAuth2Access = new OAuth2JJwtProvider(
            TokenType.ACCESS,
            accessSignatureAlgorithm,
            accessSignatureKey,
            accessExpiration
        );
        var oAuth2Refresh = new OAuth2JJwtProvider(
            TokenType.REFRESH,
            refreshSignatureAlgorithm,
            refreshSignatureKey,
            refreshExpiration
        );
        oAuth2Access.setNext(oAuth2Refresh);
        return oAuth2Access;
    }
}
