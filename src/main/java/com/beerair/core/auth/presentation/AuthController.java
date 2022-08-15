package com.beerair.core.auth.presentation;

import com.beerair.core.auth.application.RefreshTokenService;
import com.beerair.core.auth.dto.request.RefreshTokenRequest;
import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.dto.LoggedInUser;
import com.beerair.core.member.presentation.annotation.AuthUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[?] 사용자 Auth API")
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auths", produces = APPLICATION_JSON_UTF_8)
@RestController
public class AuthController {
    private final RefreshTokenService refreshTokenService;

    @ApiOperation(value = "Refresh Token 사용한 Access Token 발급 요청 api")
    @PostMapping("refresh")
    public ResponseEntity<?> issueAccessToken(@RequestBody RefreshTokenRequest request) {
        var response = refreshTokenService.issueToken(request.getRefreshToken());
        return ResponseDto.ok(response);
    }

    @ApiOperation(value = "(localhost) Auth Token 에 담긴 Principal 조회")
    @GetMapping
    public ResponseEntity<?> getTokenValue(HttpServletRequest httpServletRequest, @AuthUser Optional<LoggedInUser> loggedInUser) {
        if (!httpServletRequest.getRemoteHost().equals("0:0:0:0:0:0:0:1")) {
            return ResponseDto.notFound();
        }
        return ResponseDto.ok(loggedInUser.orElse(null));
    }
}
