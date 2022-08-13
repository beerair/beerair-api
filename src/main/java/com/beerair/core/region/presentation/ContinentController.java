package com.beerair.core.region.presentation;

import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.region.application.ContinentService;
import com.beerair.core.region.domain.vo.rs.ContinentResponses;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[4] 대륙 API")
@RestController
@RequestMapping(value = "/api/v1/continents", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class ContinentController {
    private final ContinentService continentService;

    @ApiOperation(value = "대륙 목록 조회 api")
    @GetMapping
    public ResponseEntity<ContinentResponses> getAll() {
        return ResponseDto.ok(continentService.getAll());
    }

}
