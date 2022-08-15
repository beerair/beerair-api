package com.beerair.core.unit.auth.infrastructure.jwt;

import com.beerair.core.auth.dto.response.CustomGrantedAuthority;
import com.beerair.core.auth.infrastructure.jwt.JJwtEncoder;
import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Member;
import com.beerair.core.fixture.MemberFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JJwtEncoderForOAuth2Test {
    private static final String ALGORITHM = "HS256";
    private static final String KEY = "8L49ndmVcjq6zOnWpGyQW7NoeEpAE9pP7csj1kWCnC4KOcrK";

    private OAuth2AuthenticationToken authentication;
    private Collection<GrantedAuthority> authorities;
    private OAuth2Member oAuth2Member;

    @BeforeEach
    void setUp() {
        this.authorities = Set.of(
                new CustomGrantedAuthority("ROLE_MEMBER")
        );
        this.oAuth2Member = OAuth2Member.of(
                MemberFixture.createMemberFixture().get(),
                Collections.emptyMap()
        );
        this.authentication = new OAuth2AuthenticationToken(
                oAuth2Member, authorities, "naver"
        );
    }

    @DisplayName("암/복호화")
    @Test
    void crypto() {
        var encoder = new JJwtEncoder(
            ALGORITHM, KEY, 10000
        );

        // When
        String token = encoder.encode(authentication);

        // Then
        assertThat(encoder.getLoggedInUser(token))
            .isEqualTo(oAuth2Member);
        //noinspection unchecked
        assertThat((Set<GrantedAuthority>) encoder.getAuthorities(token))
            .containsExactly(authorities.toArray(new GrantedAuthority[0]));
    }

    @DisplayName("토큰 만료")
    @Test
    void verify() {
        var provider = new JJwtEncoder(ALGORITHM, KEY, 0);

        String token = provider.encode(authentication);
        assertThatThrownBy(() -> provider.getLoggedInUser(token))
            .isInstanceOf(Exception.class); // TODO Excpetion
    }
}
