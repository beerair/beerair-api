package com.beerair.core.acceptance.config;

import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.domain.TokenPurpose;
import com.beerair.core.auth.infrastructure.oauth2.OAuth2AttributesLoader;
import com.beerair.core.auth.presentation.AuthTokenFailureHandler;
import com.beerair.core.fixture.fake.FakeAuthTokenCrypto;
import com.beerair.core.fixture.fake.FakeAuthenticationSuccessHandler;
import com.beerair.core.fixture.fake.FakeClientRegistrationRepository;
import com.beerair.core.fixture.fake.FakeDelegateOAuth2AttributesLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Profile("test")
@Configuration
public class TestSecurityBeanConfig {
    @Bean
    public OAuth2AttributesLoader oAuth2AttributesLoader() {
        return new FakeDelegateOAuth2AttributesLoader();
    }

    @Bean({ TokenPurpose.ACCESS, TokenPurpose.REFRESH })
    public AuthTokenCrypto tokenCrypto() {
        return new FakeAuthTokenCrypto();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new FakeAuthenticationSuccessHandler();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new FakeClientRegistrationRepository();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthTokenFailureHandler("/");
    }
}
