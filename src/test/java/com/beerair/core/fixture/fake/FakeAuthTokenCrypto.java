package com.beerair.core.fixture.fake;

import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.dto.response.CustomGrantedAuthority;
import com.beerair.core.error.TestDebugException;
import com.beerair.core.member.domain.Member;
import com.beerair.core.member.dto.LoggedInUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FakeAuthTokenCrypto implements AuthTokenCrypto {
    private static final Map<String, Member> memberEachToken = new HashMap<>();

    public FakeAuthTokenCrypto() {
    }

    public static void register(String token, Member member) {
        memberEachToken.put(token, member);
    }

    private static Member get(String token) {
        return Optional
                .ofNullable(memberEachToken.get(token))
                .orElseThrow(() -> new TestDebugException("사전에 등록된 token이 없습니다."));
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
    public String encrypt(AuthTokenAuthentication authentication) {
        return findTokenByMemberId(authentication.getLoggedInUser().getId());
    }

    @Override
    public AuthTokenAuthentication decrypt(String token) {
        var member = get(token);
        var loggedInUser = LoggedInUser.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .build();
        var authorities = member.getRole()
                .getAuthorities()
                .stream()
                .map(CustomGrantedAuthority::new)
                .collect(Collectors.toSet());
        return new AuthTokenAuthentication(loggedInUser, authorities);
    }
}
