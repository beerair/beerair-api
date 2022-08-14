package com.beerair.core.unit.auth.infrastructure.jwt;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

class JJwtProviderTest {
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
        final var AUTH = "MEMBER";
        var provider = new FakeJJwtProvider(
            ALGORITHM,
            KEY,
            10000
        );
        provider.setProvidable(true);
        provider.setId(ID);
        provider.setAuthorities(List.of(AUTH));

        // When
        String token = provider.encode(authentication);

        // Then
        assertThat(provider.getId(token))
            .isEqualTo(ID);
        //noinspection unchecked
        assertThat((Set<GrantedAuthority>) provider.getAuthorities(token))
            .containsExactly(new SimpleGrantedAuthority(AUTH));
    }

    @DisplayName("토큰 만료")
    @Test
    void verify() {
        var provider = new FakeJJwtProvider(ALGORITHM, KEY, 0);
        provider.setProvidable(true);

        String token = provider.encode(authentication);
        assertThatThrownBy(() -> provider.getId(token))
            .isInstanceOf(Exception.class); // TODO Excpetion
    }
}
