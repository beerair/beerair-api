package com.beerair.core.member.presentation;

import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.application.MemberService;
import com.beerair.core.member.dto.LoggedInUser;
import com.beerair.core.member.dto.request.MemberChangeNicknameRequest;
import com.beerair.core.member.dto.request.MemberSignRequest;
import com.beerair.core.member.dto.response.MemberMeResponse;
import com.beerair.core.member.presentation.annotation.AuthUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[2] 멤버 API")
@RestController
@RequestMapping(value = "/api/v1/members", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @ApiOperation(value = "회원가입 API")
    @PostMapping
    public ResponseEntity<?> sign(
            @AuthUser LoggedInUser user,
            @Valid @RequestBody MemberSignRequest request
    ) {
        memberService.sign(user, request);
        return ResponseDto.noContent();
    }

    @ApiOperation(value = "탈퇴 API")
    @DeleteMapping
    public ResponseEntity<?> resign(@AuthUser LoggedInUser user) {
        memberService.resign(user);
        return ResponseDto.noContent();
    }

    @ApiOperation(value = "닉네임 변경 API")
    @PatchMapping("/nickname")
    public ResponseEntity<?> modifiedNickname(
            @AuthUser LoggedInUser user,
            @Valid @RequestBody MemberChangeNicknameRequest request
    ) {
        memberService.changeNickname(user, request.getNickname());
        return ResponseDto.noContent();
    }

    @ApiOperation(value = "사용자 정보 조회 API")
    @GetMapping("me")
    public ResponseEntity<?> get(@AuthUser LoggedInUser user) {
        MemberMeResponse response = memberService.getMe(user);
        return ResponseDto.ok(response);
    }
}
