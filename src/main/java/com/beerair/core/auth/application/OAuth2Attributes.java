package com.beerair.core.auth.application;

import com.beerair.core.auth.domain.UserGender;
import com.beerair.core.auth.domain.UserSocialType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuth2Attributes {
    private final UserSocialType socialType;
    private final String id;
    private final String name;
    private final String email;
    private final String profile;
    private final UserGender gender;

    @Builder
    private OAuth2Attributes(UserSocialType socialType, String id, String name, String email, String profile,
                            UserGender gender) {
        this.socialType = socialType;
        this.id = id;
        this.name = name;
        this.email = email;
        this.profile = profile;
        this.gender = gender;
    }
}
