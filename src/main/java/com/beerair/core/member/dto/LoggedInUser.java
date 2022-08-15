package com.beerair.core.member.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class LoggedInUser {
    private String id;
    private String email;
    private String nickname;

    protected LoggedInUser() {
    }

    @Builder
    private LoggedInUser(String id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }
}
