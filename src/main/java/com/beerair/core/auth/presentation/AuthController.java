package com.beerair.core.auth.presentation;

import com.beerair.core.auth.application.RefreshTokenService;
import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.dto.request.RefreshTokenRequest;
import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.error.exception.auth.NoAuthException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[?] 사용자 Auth API")
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auths", produces = APPLICATION_JSON_UTF_8)
@RestController
public class AuthController {
    private final AuthTokenAuthenticationFilter authTokenAuthenticationFilter;
    private final AuthTokenCrypto authTokenCrypto;
    private final RefreshTokenService refreshTokenService;

    @ApiOperation(value = "Access Token 정보 조회")
    @GetMapping("me")
    public ResponseEntity<?> authMe(HttpServletRequest httpServletRequest) {
        var token = authTokenAuthenticationFilter.getToken(httpServletRequest)
                .orElseThrow(NoAuthException::new);
        AuthTokenAuthentication authentication = authTokenCrypto.decrypt(token);
        return ResponseDto.ok(authentication);
    }

    @ApiOperation(value = "Refresh Token 사용한 Access Token 발급 요청 api")
    @PostMapping("refresh")
    public ResponseEntity<?> issueAccessToken(@RequestBody RefreshTokenRequest request) {
        var response = refreshTokenService.issueByRefreshToken(request.getRefreshToken());
        return ResponseDto.ok(response);
    }
}
