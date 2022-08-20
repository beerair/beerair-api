package com.beerair.core.member.domain.vo;

import com.beerair.core.common.domain.StringFieldCryptConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode
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

    protected MemberSocial() {
    }

    public MemberSocial(String socialId, SocialType socialType) {
        this.socialId = socialId;
        this.socialType = socialType;
    }
}
