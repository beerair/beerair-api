package com.beerair.core.suggest.presentation;

import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.suggest.application.BeerSuggestService;
import com.beerair.core.suggest.dto.request.BeerSuggestRegisterRequest;
import com.beerair.core.suggest.facade.BeerSuggestFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
            @RequestParam("name") String name
    ) {
        // TODO : 인증, 인가 로직 붙이기
        beerSuggestFacade.validate(name, 1L);
        return ResponseDto.noContent();
    }

    @ApiOperation(value = "맥주 등록 요청 api")
    @PostMapping
    public ResponseEntity<?> register(
            @RequestBody BeerSuggestRegisterRequest request
    ) {
        // TODO : 인증, 인가 로직 붙이기
        var response = beerSuggestFacade.register(1L, request);
        return ResponseDto.created(response);
    }

    @ApiOperation(value = "요청한 맥주 목록 조회 api")
    @GetMapping
    public ResponseEntity<Void> getAll() {
        return ResponseDto.noContent();
    }

    @ApiOperation(value = "요청한 맥주 목록 count api")
    @GetMapping("/count")
    public ResponseEntity<?> count() {
        var response = beerSuggestService.count(1L);
        return ResponseDto.ok(response);
    }
}
