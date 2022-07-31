package com.beerair.core.region.presentation;

import com.beerair.core.region.application.CountryService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[3] 국가 API")
@RestController
@RequestMapping(value = "/api/countries/v1", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;
}
