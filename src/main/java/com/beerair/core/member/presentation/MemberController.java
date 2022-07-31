package com.beerair.core.member.presentation;

import com.beerair.core.member.application.MemberService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[2] 멤버 API")
@RestController
@RequestMapping(value = "/api/v1/members", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
}
