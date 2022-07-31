package com.beerair.core.suggest.presentation;

import com.beerair.core.suggest.application.BeerSuggestService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[8] 사용자 요청 API")
@RestController
@RequestMapping(value = "/api/v1/suggests", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class BeerSuggestController {
    private final BeerSuggestService beerSuggestService;
}
