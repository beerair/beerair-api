package com.beerair.core.unit.auth.infrastructure.jwt;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Set;

import com.beerair.core.member.dto.LoggedInUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.beerair.core.auth.domain.TokenType;

class JJwtEncoderTest {
    private Authentication authentication;
    private static final String ALGORITHM = "HS256";
    private static final String KEY = "8L49ndmVcjq6zOnWpGyQW7NoeEpAE9pP7csj1kWCnC4KOcrK";

    @BeforeEach
    void setUp() {
        authentication = null;
    }

    @DisplayName("암/복호화")
    @Test
    void crypto() {
        // Given
        final var ID = "ID";
        final var AUTH = "ROLE_MEMBER";
        final var LOGGED_IN_USER = LoggedInUser.builder()
                .id(ID)
                .build();
        var encoder = new FakeJJwtEncoder(
            ALGORITHM,
            KEY,
            10000
        );
        encoder.setProvidable(true);
        encoder.setLoggedInUser(LOGGED_IN_USER);
        encoder.setAuthorities(List.of(AUTH));

        // When
        String token = encoder.encode(TokenType.ACCESS, authentication);

        // Then
        assertThat(encoder.getLoggedInUser(token))
            .isEqualTo(LOGGED_IN_USER);
        //noinspection unchecked
        assertThat((Set<GrantedAuthority>) encoder.getAuthorities(token))
            .containsExactly(new SimpleGrantedAuthority(AUTH));
    }

    @DisplayName("토큰 만료")
    @Test
    void verify() {
        var provider = new FakeJJwtEncoder(ALGORITHM, KEY, 0);
        provider.setProvidable(true);

        String token = provider.encode(TokenType.ACCESS, authentication);
        assertThatThrownBy(() -> provider.getLoggedInUser(token))
            .isInstanceOf(Exception.class); // TODO Excpetion
    }
}
