package com.beerair.core.beer.presentation;

import com.beerair.core.beer.application.BeerSearchService;
import com.beerair.core.beer.application.BeerService;
import com.beerair.core.beer.dto.request.BeerSearchRequest;
import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.member.presentation.annotation.AuthMemberId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.beerair.core.common.util.CommonUtil.APPLICATION_JSON_UTF_8;

@Api(tags = "[1] 맥주 API")
@RestController
@RequestMapping(value = "/api/v1/beers", produces = APPLICATION_JSON_UTF_8)
@RequiredArgsConstructor
public class BeerController {
    private final BeerService beerService;
    private final BeerSearchService beerSearchService;

    @ApiOperation(value = "맥주 조회 API (필터,정렬,검색)")
    @GetMapping
    public ResponseEntity<?> search(
            @AuthMemberId Optional<String> memberId,
            BeerSearchRequest request) {
        var response = beerSearchService.search(
                memberId.orElse(null), request
        );
        return ResponseDto.ok(response);
    }

    @ApiOperation(value = "맥주 상세 조회 API", notes = "MOCK UP API")
    @GetMapping("/{beerId}")
    public ResponseEntity<?> get(
            @AuthMemberId Optional<String> userId,
            @PathVariable("beerId") String beerId
    ) {
        return ResponseDto.ok(
                beerService.getWithRegion(
                        userId.orElse(null), beerId
                )
        );
    }

    @ApiOperation(value = "맥주 추천 목록 조회 api")
    @GetMapping("/recommends")
    public ResponseEntity<Void> getRecommends() {
        return ResponseDto.noContent();
    }
}
