package com.beerair.core.beer.presentation;

import com.beerair.core.beer.application.BeerTypeService;
import com.beerair.core.beer.dto.response.BeerTypeResponse;
import com.beerair.core.common.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[10] 맥주 종류 API")
@RestController
@RequestMapping(value = "/api/v1/beer-types", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class BeerTypeController {
    private final BeerTypeService beerTypeService;

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "맥주 종류 목록 조회 api")
    @GetMapping
    public ResponseDto<List<BeerTypeResponse>> getAll() {
        var result = beerTypeService.getAll();
        return new ResponseDto<>(result);
    }
}
