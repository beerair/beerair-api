package com.beerair.core.auth.presentation;

import com.beerair.core.auth.application.RefreshTokenService;
import com.beerair.core.auth.dto.request.RefreshTokenRequest;
import com.beerair.core.common.dto.ResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auths", consumes = APPLICATION_JSON_UTF_8, produces = APPLICATION_JSON_UTF_8)
@RestController
public class AuthController {
    private final RefreshTokenService refreshTokenService;

    @ApiOperation(value = "Refresh Token 사용한 Access Token 발급 요청 api")
    @PostMapping("refresh")
    public ResponseEntity<?> issueAccessToken(@RequestBody RefreshTokenRequest request) {
        var response = refreshTokenService.issueToken(request.getRefreshToken());
        return ResponseDto.ok(response);
    }
}
