package com.beerair.core.auth.infrastructure.oauth2.dto;

import com.beerair.core.member.domain.vo.SocialType;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuth2Attributes {
    private final SocialType socialType;
    private final String socialId;
    private final String email;
    private final String profile;
    private final Map<String, Object> attributes;

    @Builder
    private OAuth2Attributes(
            SocialType socialType,
            String socialId,
            String email,
            String profile,
            Map<String, Object> attributes
    ) {
        this.socialType = socialType;
        this.socialId = socialId;
        this.email = email;
        this.profile = profile;
        this.attributes = attributes;
    }
}
