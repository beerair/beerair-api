package com.beerair.core.beer.presentation;

import com.beerair.core.beer.application.BeerService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[1] 맥주 API")
@RestController
@RequestMapping(value = "/api/beers/v1", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class BeerController {
    private final BeerService beerService;
}
