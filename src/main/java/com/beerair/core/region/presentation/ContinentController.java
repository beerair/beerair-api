package com.beerair.core.region.presentation;

import com.beerair.core.region.application.ContinentService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[2] 대륙 API")
@RestController
@RequestMapping(value = "/api/continents/v1", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class ContinentController {
    private final ContinentService continentService;
}
