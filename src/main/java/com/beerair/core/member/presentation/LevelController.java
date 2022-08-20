package com.beerair.core.member.presentation;

import com.beerair.core.member.application.LevelService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[3] 레벨 API")
@RestController
@RequestMapping(value = "/api/v1/levels", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class LevelController {
    private final LevelService levelService;
}
