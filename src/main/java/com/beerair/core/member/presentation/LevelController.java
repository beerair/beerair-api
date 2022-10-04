package com.beerair.core.member.presentation;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.application.LevelService;
import com.beerair.core.member.dto.response.LevelResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "[3] 레벨 API")
@RestController
@RequestMapping(value = "/api/v1/levels", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class LevelController {
    private final LevelService levelService;

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "모든 레벨 조회 api")
    @GetMapping
    public ResponseDto<List<LevelResponse>> getAll() {
        return new ResponseDto<>(levelService.getAll());
    }
}
