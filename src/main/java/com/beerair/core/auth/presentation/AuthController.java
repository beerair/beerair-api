package com.beerair.core.auth.presentation;

import com.beerair.core.auth.application.AuthTokenService;
import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.dto.LoggedInMember;
import com.beerair.core.member.presentation.annotation.AuthMember;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[0] Auth API")
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auths", produces = APPLICATION_JSON_UTF_8)
@RestController
public class AuthController {
    private final AuthTokenService refreshTokenService;

    @ApiOperation(value = "Refresh Token 사용한 Access Token 발급 요청 api")
    @PostMapping("{refreshToken}/access-token")
    public ResponseEntity<?> issueAccessToken(@PathVariable("refreshToken") String refreshToken) {
        var response = refreshTokenService.issueByRefreshToken(refreshToken);
        return ResponseDto.ok(response);
    }

    @ApiOperation(value = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@AuthMember LoggedInMember member) {
        refreshTokenService.deleteByMember(member.getId());
        return ResponseDto.noContent();
    }
}
