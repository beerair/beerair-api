package com.beerair.core.suggest.presentation;

import com.beerair.core.common.dto.PageDto;
import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.dto.LoggedInMember;
import com.beerair.core.member.presentation.annotation.AuthMember;
import com.beerair.core.suggest.application.BeerSuggestService;
import com.beerair.core.suggest.dto.request.BeerSuggestRegisterRequest;
import com.beerair.core.suggest.facade.BeerSuggestFacade;
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

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[8] 사용자 요청 API")
@RestController
@RequestMapping(value = "/api/v1/suggests", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class BeerSuggestController {
    private final BeerSuggestFacade beerSuggestFacade;
    private final BeerSuggestService beerSuggestService;

    @ApiOperation(value = "맥주 등록 요청시 validation api")
    @GetMapping("/validate")
    public ResponseEntity<?> validate(
            @RequestParam("name") String name,
            @AuthMember LoggedInMember member
    ) {
        beerSuggestFacade.validate(name, member.getId());
        return ResponseDto.noContent();
    }

    @ApiOperation(value = "맥주 등록 요청 api")
    @PostMapping
    public ResponseEntity<?> register(
            @RequestBody BeerSuggestRegisterRequest request,
            @AuthMember LoggedInMember member
    ) {
        // TODO : 인증, 인가 로직 붙이기
        var response = beerSuggestFacade.register(request, member.getId());
        return ResponseDto.created(response);
    }

    @ApiOperation(value = "요청한 맥주 목록 조회 api")
    @GetMapping
    public ResponseEntity<?> getAll(
            @PageableDefault Pageable pageable,
            @AuthMember LoggedInMember member
    ) {
        var response = beerSuggestService.getAll(pageable, member.getId());
        return PageDto.ok(response);
    }

    @ApiOperation(value = "요청한 맥주 목록 count api")
    @GetMapping("/count")
    public ResponseEntity<?> count(
            @AuthMember LoggedInMember member
    ) {
        var response = beerSuggestService.count(member.getId());
        return ResponseDto.ok(response);
    }
}
