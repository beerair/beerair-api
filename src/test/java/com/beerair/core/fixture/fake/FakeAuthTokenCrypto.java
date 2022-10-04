package com.beerair.core.fixture.fake;

import com.beerair.core.auth.domain.AuthToken;
import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.dto.response.CustomGrantedAuthority;
import com.beerair.core.auth.infrastructure.oauth2.dto.OAuth2Member;
import com.beerair.core.error.TestDebugException;
import com.beerair.core.member.domain.Member;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FakeAuthTokenCrypto implements AuthTokenCrypto {
    private static final Map<String, Member> memberEachToken = new HashMap<>();

    public FakeAuthTokenCrypto() {
    }

    public static void register(String token, Member member) {
        memberEachToken.put(token, member);
    }

    public static void cleanUp() {
        memberEachToken.clear();
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
    public AuthToken encrypt(AuthTokenAuthentication authentication) {
        return new AuthToken(
                findTokenByMemberId(authentication.getLoggedInUser().getId()),
                new Date(new Date().getTime() + 1000000)
        );
    }

    @Override
    public AuthTokenAuthentication decrypt(String token) {
        var member = memberEachToken.get(token);
        var loggedInUser = OAuth2Member.of(member, Collections.emptyMap());

        var authorities = member.getRole()
                .getAuthorities()
                .stream()
                .map(CustomGrantedAuthority::new)
                .collect(Collectors.toSet());
        return AuthTokenAuthentication.from(loggedInUser, authorities);
    }
}
