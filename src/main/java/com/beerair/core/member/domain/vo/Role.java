package com.beerair.core.member.domain.vo;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;

@Getter
public enum Role {
    USER(Set.of(
        new SimpleGrantedAuthority("user"))
    ),
    MEMBER(Set.of(
        new SimpleGrantedAuthority("member"))
    );

    private final Set<GrantedAuthority> authorities;

    Role(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
