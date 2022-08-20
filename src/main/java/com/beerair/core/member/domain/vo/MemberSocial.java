package com.beerair.core.member.domain.vo;

import com.beerair.core.common.domain.StringFieldCryptConverter;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Getter
@Embeddable
public class MemberSocial {
    @Convert(converter = StringFieldCryptConverter.class)
    @Comment("소셜 계정 ID")
    @Column(nullable = false)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Comment("소셜 계정 종류")
    @Column(length = 100, nullable = false)
    private SocialType socialType;

    @Convert(converter = StringFieldCryptConverter.class)
    @Comment("이메일")
    @Column(nullable = false, unique = true)
    private String email;

    @Convert(converter = StringFieldCryptConverter.class)
    @Comment("프로필 이미지")
    @Column(length = 500)
    private String profileUrl;

    @Convert(converter = StringFieldCryptConverter.class)
    @Comment("핸드폰 번호")
    @Column(unique = true)
    private String phoneNumber;

    protected MemberSocial() {
    }

    @Builder
    private MemberSocial(String email, String profileUrl, String socialId, SocialType socialType, String phoneNumber) {
        this.email = email;
        this.profileUrl = profileUrl;
        this.socialId = socialId;
        this.socialType = socialType;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberSocial social = (MemberSocial) o;
        return Objects.equals(socialId, social.socialId) && socialType == social.socialType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(socialId, socialType);
    }
}
