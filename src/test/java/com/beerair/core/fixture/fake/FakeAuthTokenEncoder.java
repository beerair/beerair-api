package com.beerair.core.fixture.fake;

import com.beerair.core.auth.domain.AuthTokenEncoder;
import com.beerair.core.auth.domain.TokenType;
import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Member;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.dto.LoggedInUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FakeAuthTokenEncoder implements AuthTokenEncoder {
    private static final Map<String, Member> memberEachToken = new HashMap<>();

    public static void register(String token, Member member) {
        memberEachToken.put(token, member);
    }

    @Override
    public String encode(TokenType tokenType, Authentication authentication) {
        if (authentication instanceof OAuth2Member) {
            var oAuth2Member = (OAuth2Member) authentication;
            return findTokenByMemberId(oAuth2Member.getId());
        }
        return null;
    }

    @Override
    public String encode(TokenType tokenType, LoggedInUser loggedInUser, Collection<? extends GrantedAuthority> authorities) {
        return null;
    }

    private String findTokenByMemberId(String memberId) {
        return memberEachToken
                .entrySet()
                .stream()
                .filter(each -> each.getValue().getId().equals(memberId))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new RuntimeException("사전에 등록된 Auth Token 이 없습니다."));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(String token) {
        return memberEachToken.get(token).getRole().getAuthorities();
    }

    @Override
    public LoggedInUser getLoggedInUser(String token) {
        var member = memberEachToken.get(token);
        return LoggedInUser.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .build();
    }

    @Override
    public Date getExpired(String token) {
        return null;
    }
}
