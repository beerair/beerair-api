package com.beerair.core.config;

import com.beerair.core.auth.application.RefreshTokenService;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.domain.TokenType;
import com.beerair.core.auth.infrastructure.jwt.JJwtCrypto;
import com.beerair.core.auth.infrastructure.oauth2.NaverOAuth2AttributesLoader;
import com.beerair.core.auth.infrastructure.oauth2.OAuth2AttributesLoader;
import com.beerair.core.auth.presentation.AuthTokenSuccessHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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

    @Primary
    @Bean(name = TokenType.ACCESS)
    public AuthTokenCrypto accessTokenCrypto() {
        return JJwtCrypto.builder()
                .signatureAlgorithm(accessSignatureAlgorithm)
                .signatureKey(accessSignatureKey)
                .expiration(accessExpiration)
                .build();
    }

    @Bean(name = TokenType.REFRESH)
    public AuthTokenCrypto refreshTokenCrypto() {
        return JJwtCrypto.builder()
                .signatureAlgorithm(refreshSignatureAlgorithm)
                .signatureKey(refreshSignatureKey)
                .expiration(refreshExpiration)
                .build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(
            @Value("${auth.success_redirect_uri}") String successRedirectUri,
            @Qualifier(TokenType.ACCESS) AuthTokenCrypto accessTokenCrypto,
            @Qualifier(TokenType.REFRESH) AuthTokenCrypto refreshTokenCrypto,
            RefreshTokenService refreshTokenService
    ) {
        return AuthTokenSuccessHandler.builder()
                .successRedirectUri(successRedirectUri)
                .accessTokenCrypto(accessTokenCrypto)
                .refreshTokenCrypto(refreshTokenCrypto)
                .refreshTokenService(refreshTokenService)
                .build();
    }
}
