package com.beerair.core.region.presentation;

import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.region.dto.response.ContinentResponse;
import com.beerair.core.region.dto.response.CountryResponse;
import com.beerair.core.region.facade.RegionFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[4] 대륙 API")
@RestController
@RequestMapping(value = "/api/v1/continents", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class ContinentController {
    private final RegionFacade regionFacade;

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "대륙 목록 조회 api")
    @GetMapping
    public ResponseDto<List<ContinentResponse>> getAll() {
        var result = regionFacade.getAllContinents();
        return new ResponseDto<>(result);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "특정 대륙 내 국가 목록 조회 api")
    @GetMapping(value = "/{continentId}/countries")
    public ResponseDto<List<CountryResponse>> getCountriesByContinentId(
            @PathVariable(value = "continentId") Long continentId
    ) {
        var result = regionFacade.getCountriesByContinentId(continentId);
        return new ResponseDto<>(result);
    }
}
