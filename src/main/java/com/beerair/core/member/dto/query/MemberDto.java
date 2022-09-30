package com.beerair.core.member.dto.query;

import com.beerair.core.member.domain.vo.MemberSocial;
import com.beerair.core.member.domain.vo.Role;

public interface MemberDto {
    MemberInfo getMember();
    LevelInfo getLevel();

    interface MemberInfo {
        String getId();
        MemberSocial getSocial();
        String getEmail();
        String getProfileUrl();
        String getPhoneNumber();
        String getNickname();
        Integer getExp();
        Role getRole();
    }

    interface LevelInfo {
        String getImageUrl();
        Integer getTier();
    }
}
