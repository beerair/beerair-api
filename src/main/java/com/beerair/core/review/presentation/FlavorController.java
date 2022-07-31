package com.beerair.core.review.presentation;

import com.beerair.core.review.application.FlavorService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[6] 맛 API")
@RestController
@RequestMapping(value = "/api/v1/flavors", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class FlavorController {
    private final FlavorService flavorService;
}
