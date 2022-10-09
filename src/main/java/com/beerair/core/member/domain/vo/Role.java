package com.beerair.core.member.domain.vo;

import java.util.Set;
import lombok.Getter;

@Getter
public enum Role {
    USER(Set.of("ROLE_USER")),
    MEMBER(Set.of("ROLE_MEMBER"));

    private final Set<String> authorities;

    Role(Set<String> authorities) {
        this.authorities = authorities;
    }
}
