package com.beerair.core.config.security;

import com.beerair.core.auth.application.AuthTokenService;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.domain.TokenPurpose;
import com.beerair.core.auth.infrastructure.jwt.JwtCrypto;
import com.beerair.core.auth.infrastructure.oauth2.KakaoOAuth2AttributesLoader;
import com.beerair.core.auth.infrastructure.oauth2.NaverOAuth2AttributesLoader;
import com.beerair.core.auth.infrastructure.oauth2.OAuth2AttributesLoader;
import com.beerair.core.auth.presentation.loginhandler.AuthTokenFailureHandler;
import com.beerair.core.auth.presentation.loginhandler.AuthTokenSuccessHandler;
import com.beerair.core.auth.presentation.loginhandler.TokenDelivery;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Profile({ "local", "prod", "staging" })
@Configuration
public class SecurityBeanConfig {
    @Value("${auth.jjwt.access.signatureAlgorithm}")
    private String accessSignatureAlgorithm;
    @Value("${auth.jjwt.access.signatureKey}")
    private String accessSignatureKey;
    @Value("${auth.jjwt.access.expiration}")
    private long accessExpiration;

    @Value("${auth.jjwt.refresh.signatureAlgorithm}")
    private String refreshSignatureAlgorithm;
    @Value("${auth.jjwt.refresh.signatureKey}")
    private String refreshSignatureKey;
    @Value("${auth.jjwt.refresh.expiration}")
    private int refreshExpiration;


    @Bean
    public OAuth2AttributesLoader oAuth2AttributesLoader() {
        var naver = new NaverOAuth2AttributesLoader();
        var kakao = new KakaoOAuth2AttributesLoader();
        naver.setNext(kakao);
        return naver;
    }

    @Primary
    @Bean(name = TokenPurpose.ACCESS)
    public AuthTokenCrypto accessTokenCrypto() {
        return JwtCrypto.builder()
                .tokenPurpose(TokenPurpose.ACCESS)
                .signatureAlgorithm(accessSignatureAlgorithm)
                .signatureKey(accessSignatureKey)
                .expiration(accessExpiration * 24 * 365) // TODO 무제한 엑세스 토큰 발급 위함
                .build();
    }

    @Bean(name = TokenPurpose.REFRESH)
    public AuthTokenCrypto refreshTokenCrypto() {
        return JwtCrypto.builder()
                .tokenPurpose(TokenPurpose.REFRESH)
                .signatureAlgorithm(refreshSignatureAlgorithm)
                .signatureKey(refreshSignatureKey)
                .expiration(refreshExpiration)
                .build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(
            AuthTokenService refreshTokenService,
            @Value("${auth.success_redirect_uri}") String successRedirectUri,
            @Qualifier(TokenPurpose.ACCESS) AuthTokenCrypto accessTokenCrypto,
            @Qualifier(TokenPurpose.REFRESH) AuthTokenCrypto refreshTokenCrypto,
            TokenDelivery tokenDeliver
    ) {
        return AuthTokenSuccessHandler.builder()
                .refreshTokenService(refreshTokenService)
                .successRedirectUri(successRedirectUri)
                .accessTokenCrypto(accessTokenCrypto)
                .refreshTokenCrypto(refreshTokenCrypto)
                .refreshTokenService(refreshTokenService)
                .tokenDeliver(tokenDeliver)
                .build();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(@Value("${auth.fail_redirect_uri}") String redirectUrl) {
        return new AuthTokenFailureHandler(redirectUrl);
    }
}
