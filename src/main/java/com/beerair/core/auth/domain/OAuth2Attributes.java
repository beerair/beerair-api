package com.beerair.core.auth.domain;

import com.beerair.core.member.domain.vo.SocialType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuth2Attributes {
    private final SocialType socialType;
    private final String id;
    private final String name;
    private final String email;
    private final String profile;

    @Builder
    private OAuth2Attributes(SocialType socialType, String id, String name, String email, String profile) {
        this.socialType = socialType;
        this.id = id;
        this.name = name;
        this.email = email;
        this.profile = profile;
    }
}
