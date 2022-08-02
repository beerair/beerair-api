package com.beerair.core.beer.presentation;

import com.beerair.core.beer.application.BeerTypeService;
import com.beerair.core.common.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[10] 맥주 종류 API")
@RestController
@RequestMapping(value = "/api/v1/beer-types", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class BeerTypeController {
    private final BeerTypeService beerTypeService;

    @ApiOperation(value = "맥주 종류 목록 조회 api")
    @GetMapping
    public ResponseEntity<Void> getAll() {
        return ResponseDto.noContent();
    }
}
