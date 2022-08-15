package com.beerair.core.config;

import com.beerair.core.auth.application.RefreshTokenProvider;
import com.beerair.core.auth.presentation.AuthTokenSuccessHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.beerair.core.auth.domain.AuthTokenEncoder;
import com.beerair.core.auth.domain.TokenType;
import com.beerair.core.auth.infrastructure.oauth2.OAuth2AttributesLoader;
import com.beerair.core.auth.infrastructure.jwt.OAuth2JJwtEncoder;
import com.beerair.core.auth.infrastructure.oauth2.NaverOAuth2AttributesLoader;
import org.springframework.context.annotation.Profile;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Profile({ "local", "prod", "staging" })
@Configuration
public class SecurityBeanConfig {
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
    public AuthTokenEncoder oAuth2AccessEncoder() {
        var oAuth2Access = new OAuth2JJwtEncoder(
            TokenType.ACCESS,
            accessSignatureAlgorithm,
            accessSignatureKey,
            accessExpiration
        );
        var oAuth2Refresh = new OAuth2JJwtEncoder(
            TokenType.REFRESH,
            refreshSignatureAlgorithm,
            refreshSignatureKey,
            refreshExpiration
        );
        oAuth2Access.setNext(oAuth2Refresh);
        return oAuth2Access;
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(
            @Value("${auth.success_redirect_uri}") String successRedirectUri,
            AuthTokenEncoder authTokenProvider,
            RefreshTokenProvider refreshTokenService
    ) {
        return new AuthTokenSuccessHandler(successRedirectUri, authTokenProvider, refreshTokenService);
    }
}
