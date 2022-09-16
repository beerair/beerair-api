package com.beerair.core.auth.presentation;

import com.beerair.core.auth.application.AuthTokenService;
import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.dto.LoggedInMember;
import com.beerair.core.member.presentation.annotation.AuthMember;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[0] Auth API")
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1", produces = APPLICATION_JSON_UTF_8)
@RestController
public class AuthController {
    private final AuthTokenService refreshTokenService;

    @ApiOperation(value = "로그아웃")
    @PostMapping("logout")
    public ResponseEntity<?> logout(HttpServletResponse response, @AuthMember LoggedInMember member) {
        refreshTokenService.deleteRefreshTokenByMember(member.getId());
        deleteCookie("accessToken", response);
        deleteCookie("refreshToken", response);
        return ResponseDto.noContent();
    }

    private void deleteCookie(String cookieName, HttpServletResponse response) {
        var cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
