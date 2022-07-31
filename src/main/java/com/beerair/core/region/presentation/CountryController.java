package com.beerair.core.region.presentation;

import com.beerair.core.region.application.CountryService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[5] 국가 API")
@RestController
@RequestMapping(value = "/api/v1/countries", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;
}
