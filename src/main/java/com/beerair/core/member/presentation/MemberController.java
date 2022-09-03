package com.beerair.core.member.presentation;

import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.application.MemberService;
import com.beerair.core.member.dto.LoggedInMember;
import com.beerair.core.member.dto.request.MemberChangeNicknameRequest;
import com.beerair.core.member.dto.request.MemberSignRequest;
import com.beerair.core.member.dto.response.MemberResponse;
import com.beerair.core.member.facade.MemberSignFacade;
import com.beerair.core.member.presentation.annotation.AuthMember;
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
    private final MemberSignFacade memberSignFacade;

    @ApiOperation(value = "회원가입 API")
    @PostMapping
    public ResponseEntity<?> sign(
            @AuthMember LoggedInMember member,
            @Valid @RequestBody MemberSignRequest request
    ) {
        memberSignFacade.sign(member, request);
        return ResponseDto.noContent();
    }

    @ApiOperation(value = "탈퇴 API")
    @DeleteMapping
    public ResponseEntity<?> resign(@AuthMember LoggedInMember member) {
        memberService.resign(member);
        return ResponseDto.noContent();
    }

    @ApiOperation(value = "닉네임 변경 API")
    @PatchMapping("/nickname")
    public ResponseEntity<?> modifiedNickname(
            @AuthMember LoggedInMember member,
            @Valid @RequestBody MemberChangeNicknameRequest request
    ) {
        memberService.changeNickname(member, request.getNickname());
        return ResponseDto.noContent();
    }

    @ApiOperation(value = "사용자 정보 조회 API")
    @GetMapping("me")
    public ResponseEntity<?> get(@AuthMember LoggedInMember member) {
        MemberResponse response = memberService.getMe(member);
        return ResponseDto.ok(response);
    }
}
