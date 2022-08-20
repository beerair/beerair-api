package com.beerair.core.member.presentation;

import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.application.MemberValidationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[2] 멤버 Validation API")
@RestController
@RequestMapping(value = "/api/v1/members/validate", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class MemberValidationController {
    private final MemberValidationService memberValidationService;

    @ApiOperation(value = "nickname 유효성 검사 API")
    @GetMapping("nickname")
    public ResponseEntity<?> nickname(
            @Length(min = 3, max = 15)
            @RequestParam("nickname") String nickname) {
        memberValidationService.verifyNickname(nickname);
        return ResponseDto.noContent();
    }
}
