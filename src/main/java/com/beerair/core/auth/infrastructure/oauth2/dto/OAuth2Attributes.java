package com.beerair.core.auth.infrastructure.oauth2.dto;

import com.beerair.core.member.domain.vo.SocialType;
import java.util.Map;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuth2Attributes {
    @NotNull(message = "지원하지 않는 로그인 입니다.")
    private final SocialType socialType;

    @NotNull(message = "올바르지 않은 로그인 입니다.")
    private final String socialId;

    @NotNull(message = "이메일 정보 제공에 동의 해주세요.")
    private final String email;

    private final String profile;

    private final String phoneNumber;

    @NotNull
    private final Map<String, Object> attributes;

    @Builder
    private OAuth2Attributes(
            SocialType socialType,
            String socialId,
            String email,
            String profile,
            String phoneNumber,
            Map<String, Object> attributes
    ) {
        this.socialType = socialType;
        this.socialId = socialId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profile = profile;
        this.attributes = attributes;
    }
}
