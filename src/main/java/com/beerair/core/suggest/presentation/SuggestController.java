package com.beerair.core.suggest.presentation;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

import com.beerair.core.common.dto.PageDto;
import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.dto.LoggedInMember;
import com.beerair.core.member.presentation.annotation.AuthMember;
import com.beerair.core.suggest.application.SuggestService;
import com.beerair.core.suggest.dto.request.SuggestRegisterRequest;
import com.beerair.core.suggest.dto.response.SuggestCountResponse;
import com.beerair.core.suggest.dto.response.SuggestRegisterResponse;
import com.beerair.core.suggest.dto.response.SuggestResponse;
import com.beerair.core.suggest.facade.SuggestFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "[8] 사용자 요청 API")
@RestController
@RequestMapping(value = "/api/v1/suggests", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class SuggestController {
    private final SuggestFacade suggestFacade;
    private final SuggestService suggestService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "맥주 등록 요청시 validation api")
    @GetMapping("/validate")
    public ResponseDto<Void> validate(
            @AuthMember Optional<LoggedInMember> member,
            @RequestParam("name") String name
    ) {
        suggestFacade.validate(
                member.map(LoggedInMember::getId).orElse(null),
                name
        );
        return new ResponseDto<>();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "맥주 등록 요청 api")
    @PostMapping
    public ResponseDto<SuggestRegisterResponse> register(
            @AuthMember Optional<LoggedInMember> member,
            @RequestBody SuggestRegisterRequest request
    ) {
        var result = suggestFacade.register(
                member.map(LoggedInMember::getId).orElse(null),
                request
        );
        return new ResponseDto<>(result);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "요청한 맥주 목록 조회 api")
    @GetMapping
    public PageDto<SuggestResponse> getAll(
            @AuthMember Optional<LoggedInMember> member,
            @PageableDefault Pageable pageable
    ) {
        var result = suggestService.getAll(
                member.map(LoggedInMember::getId).orElse(null),
                pageable
        );
        return new PageDto<>(result);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "요청한 맥주 목록 count api")
    @GetMapping("/count")
    public ResponseDto<SuggestCountResponse> count(
            @AuthMember Optional<LoggedInMember> member
    ) {
        var result = suggestService.count(
                member.map(LoggedInMember::getId).orElse(null)
        );
        return new ResponseDto<>(result);
    }
}
