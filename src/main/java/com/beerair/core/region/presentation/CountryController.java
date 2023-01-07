package com.beerair.core.region.presentation;

import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.region.dto.response.CountryResponse;
import com.beerair.core.region.facade.RegionFacade;
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

@Api(tags = "[5] 국가 API")
@RestController
@RequestMapping(value = "/api/v1/countries", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class CountryController {
    private final RegionFacade regionFacade;

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "국가 목록 조회 api")
    @GetMapping
    public ResponseDto<List<CountryResponse>> getAll() {
        var result = regionFacade.getAllCountries();
        return new ResponseDto<>(result);
    }
}
