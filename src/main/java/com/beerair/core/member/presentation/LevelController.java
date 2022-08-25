package com.beerair.core.member.presentation;

import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.application.LevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[3] 레벨 API")
@RestController
@RequestMapping(value = "/api/v1/levels", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class LevelController {
    private final LevelService levelService;

    @ApiOperation(value = "모든 레벨 조회 api")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseDto.ok(levelService.getAll());
    }
}