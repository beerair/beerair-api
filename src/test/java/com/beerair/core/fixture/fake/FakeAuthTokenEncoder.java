package com.beerair.core.fixture.fake;

import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenEncoder;
import com.beerair.core.auth.dto.response.CustomGrantedAuthority;
import com.beerair.core.error.TestDebugException;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.dto.LoggedInUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FakeAuthTokenEncoder implements AuthTokenEncoder {
    private static final Map<String, Member> memberEachToken = new HashMap<>();

    public static void register(String token, Member member) {
        memberEachToken.put(token, member);
    }

    private static Member get(String token) {
        return Optional
                .ofNullable(memberEachToken.get(token))
                .orElseThrow(() -> new TestDebugException("사전에 등록된 token이 없습니다."));
    }

    @Override
    public String encode(OAuth2AuthenticationToken authentication) {
        return findTokenByMemberId(authentication.getPrincipal().getName());
    }

    private String findTokenByMemberId(String memberId) {
        return memberEachToken
                .entrySet()
                .stream()
                .filter(each -> each.getValue().getId().equals(memberId))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new TestDebugException("사전에 등록된 memberId가 없습니다."));
    }

    @Override
    public String encode(LoggedInUser loggedInUser, Collection<? extends GrantedAuthority> authorities) {
        return findTokenByMemberId(loggedInUser.getId());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(String token) {
        return get(token)
                .getRole()
                .getAuthorities()
                .stream()
                .map(CustomGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public LoggedInUser getLoggedInUser(String token) {
        var member = get(token);
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
