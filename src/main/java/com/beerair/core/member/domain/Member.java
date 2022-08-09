package com.beerair.core.member.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.beerair.core.common.domain.BaseEntity;
import com.beerair.core.member.domain.vo.Role;
import com.beerair.core.member.domain.vo.SocialType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    private String id;

    private String email;

    private String nickname;

    private String phoneNumber;

    private String profileUrl;

    private Role role;

    private String sociaiId;

    private SocialType socialType;

    private Integer exp;

    private Long leverId;

    @Builder
    private Member(String email, String nickname, String phoneNumber, String profileUrl, Role role, String sociaiId,
                  SocialType socialType, Integer exp, Long leverId) {
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.profileUrl = profileUrl;
        this.role = role;
        this.sociaiId = sociaiId;
        this.socialType = socialType;
        this.exp = exp;
        this.leverId = leverId;
    }
}
