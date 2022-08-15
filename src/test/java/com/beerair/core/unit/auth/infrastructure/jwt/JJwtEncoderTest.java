package com.beerair.core.unit.auth.infrastructure.jwt;

import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.dto.response.CustomGrantedAuthority;
import com.beerair.core.auth.infrastructure.jwt.JJwtEncoder;
import com.beerair.core.member.dto.LoggedInUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JJwtEncoderTest {
    private static final String ALGORITHM = "HS256";
    private static final String KEY = "8L49ndmVcjq6zOnWpGyQW7NoeEpAE9pP7csj1kWCnC4KOcrK";

    private AuthTokenAuthentication authentication;
    private Collection<GrantedAuthority> authorities;
    private LoggedInUser loggedInUser;

    @BeforeEach
    void setUp() {
        this.authorities = Set.of(
                new CustomGrantedAuthority("ROLE_MEMBER")
        );
        this.loggedInUser = LoggedInUser.builder()
                .id("id")
                .email("email1234@naver.com")
                .nickname("nickname")
                .build();
        this.authentication = AuthTokenAuthentication.builder()
                .token("token")
                .loggedInUser(loggedInUser)
                .authorities(authorities)
                .build();
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
            .isEqualTo(loggedInUser);
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
