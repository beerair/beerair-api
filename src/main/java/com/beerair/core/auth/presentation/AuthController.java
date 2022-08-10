package com.beerair.core.auth.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beerair.core.auth.application.AuthService;
import com.beerair.core.auth.application.dto.request.OAuth2TokenRequest;
import com.beerair.core.auth.application.dto.response.AuthResponse;
import com.beerair.core.common.dto.ResponseDto;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("api/v1/auths")
@RestController
public class AuthController {
    private final AuthService authService;

    @ApiOperation(value = "소셜 로그인 API", notes = "MOCK UP API")
    @PostMapping("/oauth2")
    public ResponseEntity<?> login(@RequestBody OAuth2TokenRequest request) {
        var response = authService.loginBySocial(request);
        return ResponseDto.ok(response);
    }
}
