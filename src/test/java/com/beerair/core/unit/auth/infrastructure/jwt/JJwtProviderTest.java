package com.beerair.core.unit.auth.infrastructure.jwt;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

class JJwtProviderTest {
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        authentication = null;
    }

    @DisplayName("암/복호화")
    @Test
    void crypto() {
        // Given
        final var ID = "ID";
        final GrantedAuthority AUTHORITY = new SimpleGrantedAuthority("1");
        var provider = new FakeJJwtProvider("HS256", "1234", 1000);
        provider.setProvidable(true);
        provider.setId(ID);
        provider.setAuthorities(Set.of(AUTHORITY));

        // When
        String token = provider.encode(authentication);

        // Then
        assertThat(provider.getId(token))
            .isEqualTo(ID);
        assertThat(provider.getAuthorities(token).contains(AUTHORITY))
            .isTrue();
    }

    @DisplayName("토큰 만료")
    @Test
    void verify() {
        var provider = new FakeJJwtProvider("HS256", "1234", 0);
        provider.setProvidable(true);

        String token = provider.encode(authentication);
        assertThatThrownBy(() -> provider.getId(token))
            .isInstanceOf(Exception.class); // TODO Excpetion
    }
}
