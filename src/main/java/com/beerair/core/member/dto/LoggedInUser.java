package com.beerair.core.member.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@Getter
@Builder
@RequiredArgsConstructor
public class LoggedInUser {
    private final String id;
    private final String email;
    private final String nickname;
}
