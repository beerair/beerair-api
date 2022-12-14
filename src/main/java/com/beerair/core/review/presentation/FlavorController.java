package com.beerair.core.review.presentation;

import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.review.application.FlavorService;
import com.beerair.core.review.dto.response.FlavorRankResponse;
import com.beerair.core.review.dto.response.FlavorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[6] 맛 API")
@RestController
@RequestMapping(value = "/api/v1/flavors", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class FlavorController {
    private final FlavorService flavorService;

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "맛 목록 조회 api")
    @GetMapping
    public ResponseDto<List<FlavorResponse>> getAll() {
        var result = flavorService.getAll();
        return new ResponseDto<>(result);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "특정 맥주의 맛 Rank API")
    @GetMapping("rank")
    public ResponseDto<List<FlavorRankResponse>> getFlavorTop3(
        @RequestParam("beerId") Integer beerId,
        @RequestParam("limit") Integer limit
    ) {
        var result = flavorService.getFlavorRank(beerId, limit);
        return new ResponseDto<>(result);
    }
}
