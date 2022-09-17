package com.beerair.core.auth.presentation;

import com.beerair.core.auth.application.AuthTokenService;
import com.beerair.core.auth.utils.CookieUtils;
import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.dto.LoggedInMember;
import com.beerair.core.member.presentation.annotation.AuthMember;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[0] Auth API")
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auths", produces = APPLICATION_JSON_UTF_8)
@RestController
public class AuthController {
    private final AuthTokenService authTokenService;

    @ApiOperation(value = "로그아웃")
    @PostMapping("logout")
    public ResponseEntity<?> logout(HttpServletResponse response, @AuthMember LoggedInMember member) {
        authTokenService.deleteRefreshTokenByMember(member.getId());
        CookieUtils.deleteCookie("accessToken", response);
        CookieUtils.deleteCookie("refreshToken", response);
        return ResponseDto.noContent();
    }

    @ApiOperation(value = "권한 정보 조회")
    @GetMapping("me/authorities")
    public ResponseEntity<?> logout() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authorities = authentication.getAuthorities();
        return ResponseDto.ok(authorities);
    }
}
