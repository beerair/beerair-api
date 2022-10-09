package com.beerair.core.member.presentation;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

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
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "[2] 멤버 API")
@RestController
@RequestMapping(value = "/api/v1/members", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberSignFacade memberSignFacade;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "회원가입 API")
    @PostMapping
    public ResponseDto<Void> sign(
            @AuthMember LoggedInMember member,
            @Valid @RequestBody MemberSignRequest request
    ) {
        memberSignFacade.sign(member, request);
        return new ResponseDto<>();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "탈퇴 API")
    @DeleteMapping
    public ResponseDto<Void> resign(@AuthMember LoggedInMember member) {
        memberService.resign(member);
        return new ResponseDto<>();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "닉네임 변경 API")
    @PatchMapping("/nickname")
    public ResponseDto<Void> modifyNickname(
            @AuthMember LoggedInMember member,
            @Valid @RequestBody MemberChangeNicknameRequest request
    ) {
        memberService.changeNickname(member, request.getNickname());
        return new ResponseDto<>();
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "사용자 정보 조회 API")
    @GetMapping("me")
    public ResponseDto<MemberResponse> get(@AuthMember LoggedInMember member) {
        MemberResponse result = memberService.getMe(member);
        return new ResponseDto<>(result);
    }
}
