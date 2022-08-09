package com.beerair.core.member.domain;


import com.beerair.core.common.domain.BaseEntity;
import com.beerair.core.member.domain.vo.Role;
import com.beerair.core.member.domain.vo.SocialType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nickname;

    private String phoneNumber;

    private String profileUrl;

    private Role role;

    private String sociaiId;

    private SocialType socialType;

    private Integer exp;

    private Long leverId;
}
