package com.beerair.core.region.presentation;

import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.region.application.CountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[5] 국가 API")
@RestController
@RequestMapping(value = "/api/v1/countries", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @ApiOperation(value = "국가 목록 조회 api")
    @GetMapping
    public ResponseEntity<Void> getAll(@RequestParam("continentId") Long continentId) {
        return ResponseDto.noContent();
    }

}
