package com.beerair.core.auth.infrastructure.oauth2.dto;

import com.beerair.core.member.domain.vo.SocialType;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Getter
public class OAuth2Attributes {
    @NotNull(message = "지원하지 않는 로그인 입니다.")
    private final SocialType socialType;

    @NotNull(message = "올바르지 않은 로그인 입니다.")
    private final String socialId;

    @NotNull(message = "이메일 정보 제공에 동의 해주세요.")
    private final String email;

    private final String profile;

    @NotNull(message = "휴대폰 번호 정보 제공에 동의 해주세요.")
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
