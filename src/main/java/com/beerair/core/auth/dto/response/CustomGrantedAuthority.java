package com.beerair.core.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@Schema(description = "직렬화를 위한 권한 DTO (Response에 사용 되지 않음)")
@EqualsAndHashCode
@ToString
@Getter
public class CustomGrantedAuthority implements GrantedAuthority {
    @Schema(defaultValue = "권한")
    private String authority;

    protected CustomGrantedAuthority() {
    }

    public CustomGrantedAuthority(String authority) {
        this.authority = authority;
    }
}
