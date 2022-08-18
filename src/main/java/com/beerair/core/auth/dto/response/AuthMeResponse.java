package com.beerair.core.auth.dto.response;

import com.beerair.core.auth.domain.AuthTokenAuthentication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Getter
public class AuthMeResponse {
    private Date expired;
    private List<String> roles;

    public static AuthMeResponse from(AuthTokenAuthentication authentication) {
        var roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new AuthMeResponse(authentication.getExpired(), roles);
    }
}
