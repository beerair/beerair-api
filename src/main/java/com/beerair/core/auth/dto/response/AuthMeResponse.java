package com.beerair.core.auth.dto.response;

import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.common.util.TimeUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Getter
public class AuthMeResponse {
    private LocalDateTime expired;
    private List<String> roles;

    public static AuthMeResponse from(AuthTokenAuthentication authentication) {
        var roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        var localExpired = TimeUtil.from(authentication.getExpired().toInstant());
        return new AuthMeResponse(localExpired, roles);
    }
}
