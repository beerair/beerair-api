package com.beerair.core.beer.presentation;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

import com.beerair.core.beer.application.BeerService;
import com.beerair.core.beer.dto.request.BeerSearchRequest;
import com.beerair.core.beer.dto.response.BeerResponse;
import com.beerair.core.beer.dto.response.BeerSearchResponse;
import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.dto.LoggedInMember;
import com.beerair.core.member.presentation.annotation.AuthMember;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "[1] 맥주 API")
@RestController
@RequestMapping(value = "/api/v1/beers", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class BeerController {
    private final BeerService beerService;

    @ApiOperation(value = "맥주 조회 API (필터,정렬,검색)")
    @GetMapping
    public ResponseDto<BeerSearchResponse> search(
            @AuthMember Optional<LoggedInMember> member,
            BeerSearchRequest request
    ) {
        var result = beerService.search(
                member.map(LoggedInMember::getId).orElse(null), request
        );
        return new ResponseDto<>(result);
    }

    @ApiOperation(value = "맥주 상세 조회 API")
    @GetMapping("/{beerId}")
    public ResponseDto<BeerResponse> get(
            @AuthMember Optional<LoggedInMember> member,
            @PathVariable("beerId") Integer beerId
    ) {
        var result = beerService.getWithRegion(
                member.map(LoggedInMember::getId).orElse(null), beerId
        );
        return new ResponseDto<>(result);
    }

    @ApiOperation(value = "맥주 추천 목록 조회 api")
    @GetMapping("/recommends")
    public ResponseDto<List<BeerResponse>> getRecommends(@AuthMember LoggedInMember member) {
        var result = beerService.getRecommends(member.getId());
        return new ResponseDto<>(result);
    }
}
