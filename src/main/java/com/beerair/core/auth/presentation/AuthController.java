package com.beerair.core.auth.presentation;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

import com.beerair.core.auth.application.AuthTokenService;
import com.beerair.core.auth.utils.CookieUtils;
import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.dto.LoggedInMember;
import com.beerair.core.member.presentation.annotation.AuthMember;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "[0] Auth API")
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auths", produces = APPLICATION_JSON_UTF_8)
@RestController
public class AuthController {
    private final AuthTokenService authTokenService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "로그아웃")
    @PostMapping("logout")
    public ResponseDto<Void> logout(HttpServletResponse response, @AuthMember LoggedInMember member) {
        authTokenService.deleteRefreshTokenByMember(member.getId());
        CookieUtils.deleteCookie("accessToken", response);
        CookieUtils.deleteCookie("refreshToken", response);
        return new ResponseDto<>();
    }
}
