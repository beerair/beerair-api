package com.beerair.core.review.presentation;

import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.review.application.FlavorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[6] 맛 API")
@RestController
@RequestMapping(value = "/api/v1/flavors", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class FlavorController {
    private final FlavorService flavorService;

    @ApiOperation(value = "맛 목록 조회 api")
    @GetMapping
    public ResponseEntity<Void> readFlavors() {
        return ResponseDto.noContent();
    }

    @ApiOperation(value = "특정 맥주에 대해 기록된 맛 Top 3 조회 api")
    @GetMapping("populars")
    public ResponseEntity<Void> extractPopularFlavorWithCount(@RequestParam("beerId") Long beerId) {
        return ResponseDto.noContent();
    }

}
