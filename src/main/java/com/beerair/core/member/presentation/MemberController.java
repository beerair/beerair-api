package com.beerair.core.member.presentation;

import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.application.MemberService;
import com.beerair.core.member.dto.LoggedInUser;
import com.beerair.core.member.dto.request.MemberSignRequest;
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

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[2] 멤버 API")
@RestController
@RequestMapping(value = "/api/v1/members", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @ApiOperation(value = "회원가입 API", notes = "MOCK UP API")
    @PostMapping
    public ResponseEntity<?> sign(@AuthUser LoggedInUser user, @RequestBody MemberSignRequest request) {
        return ResponseDto.created("ok");
    }

    @ApiOperation(value = "탈퇴 API", notes = "MOCK UP API")
    @DeleteMapping("/resign")
    public ResponseEntity<?> resign() {
        return ResponseDto.ok("ok");
    }

    @ApiOperation(value = "닉네임 변경 API", notes = "MOCK UP API")
    @PatchMapping("/nickname")
    public ResponseEntity<?> modifiedNickname() {
        return ResponseDto.ok("ok");
    }

    @ApiOperation(value = "사용자 정보 조회 API", notes = "MOCK UP API")
    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseDto.ok("ok");
    }
}
