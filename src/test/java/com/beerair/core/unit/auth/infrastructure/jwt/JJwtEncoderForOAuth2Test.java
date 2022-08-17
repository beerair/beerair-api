package com.beerair.core.unit.auth.infrastructure.jwt;

import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.dto.response.CustomGrantedAuthority;
import com.beerair.core.auth.infrastructure.jwt.JJwtCrypto;
import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Member;
import com.beerair.core.error.exception.auth.ExpiredAuthTokenException;
import com.beerair.core.fixture.Fixture;
import com.beerair.core.fixture.MemberFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JJwtEncoderForOAuth2Test {
    private static final String ALGORITHM = "HS256";
    private static final String KEY = "8L49ndmVcjq6zOnWpGyQW7NoeEpAE9pP7csj1kWCnC4KOcrK";

    private Fixture<JJwtCrypto> crypto;
    private AuthTokenAuthentication authentication;
    private Collection<GrantedAuthority> authorities;
    private OAuth2Member oAuth2Member;

    @BeforeEach
    void setUp() {
        var crypto = JJwtCrypto.builder()
                .signatureAlgorithm(ALGORITHM)
                .signatureKey(KEY)
                .build();
        this.crypto = new Fixture<>(crypto);
        this.authorities = Set.of(
                new CustomGrantedAuthority("ROLE_MEMBER")
        );
        this.oAuth2Member = OAuth2Member.of(
                MemberFixture.createSocialMemberFixture().get(),
                Collections.emptyMap()
        );
        this.authentication = new AuthTokenAuthentication(
                oAuth2Member, authorities
        );
    }

    @DisplayName("암/복호화")
    @Test
    void crypt() {
        var crypto = this.crypto.set("expiration", 10000).get();

        // When
        String token = crypto.encrypt(authentication);

        // Then
        var authentication = crypto.decrypt(token);
        assertThat(authentication.getPrincipal())
            .isEqualTo(oAuth2Member);
        //noinspection
        assertThat(authentication.getAuthorities())
            .containsExactly(authorities.toArray(new GrantedAuthority[0]));
    }

    @DisplayName("토큰 만료")
    @Test
    void verify() {
        var crypto = this.crypto.set("expiration", 0).get();

        String token = crypto.encrypt(authentication);

        assertThatThrownBy(() -> crypto.decrypt(token))
            .isInstanceOf(ExpiredAuthTokenException.class);
    }
}
