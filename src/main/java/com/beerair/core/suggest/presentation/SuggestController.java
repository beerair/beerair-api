package com.beerair.core.suggest.presentation;

import com.beerair.core.common.dto.PageDto;
import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.dto.LoggedInMember;
import com.beerair.core.member.presentation.annotation.AuthMember;
import com.beerair.core.suggest.application.SuggestService;
import com.beerair.core.suggest.dto.request.SuggestRegisterRequest;
import com.beerair.core.suggest.facade.SuggestFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[8] 사용자 요청 API")
@RestController
@RequestMapping(value = "/api/v1/suggests", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class SuggestController {
    private final SuggestFacade suggestFacade;
    private final SuggestService suggestService;

    @ApiOperation(value = "맥주 등록 요청시 validation api")
    @GetMapping("/validate")
    public ResponseEntity<?> validate(
            @AuthMember Optional<LoggedInMember> member,
            @RequestParam("name") String name
    ) {
        suggestFacade.validate(
                member.map(LoggedInMember::getId).orElse(null),
                name
        );
        return ResponseDto.noContent();
    }

    @ApiOperation(value = "맥주 등록 요청 api")
    @PostMapping
    public ResponseEntity<?> register(
            @AuthMember Optional<LoggedInMember> member,
            @RequestBody SuggestRegisterRequest request
    ) {
        var response = suggestFacade.register(
                member.map(LoggedInMember::getId).orElse(null),
                request
        );

        return ResponseDto.created(response);
    }

    @ApiOperation(value = "요청한 맥주 목록 조회 api")
    @GetMapping
    public ResponseEntity<?> getAll(
            @AuthMember Optional<LoggedInMember> member,
            @PageableDefault Pageable pageable
    ) {
        var response = suggestService.getAll(
                member.map(LoggedInMember::getId).orElse(null),
                pageable
        );

        return PageDto.ok(response);
    }

    @ApiOperation(value = "요청한 맥주 목록 count api")
    @GetMapping("/count")
    public ResponseEntity<?> count(
            @AuthMember Optional<LoggedInMember> member
    ) {
        var response = suggestService.count(
                member.map(LoggedInMember::getId).orElse(null)
        );

        return ResponseDto.ok(response);
    }
}
