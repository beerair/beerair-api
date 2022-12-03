package com.beerair.core.review.application;

import com.beerair.core.common.dto.CursorPageDto;
import com.beerair.core.common.util.CursorPagingUtil;
import com.beerair.core.error.exception.review.ReviewNotFoundException;
import com.beerair.core.review.domain.Review;
import com.beerair.core.review.dto.response.ReviewResponse;
import com.beerair.core.review.infrastructure.ReviewQueryRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewQueryService {
    private final ReviewQueryRepository reviewQueryRepository;

    public ReviewResponse get(String memberId, Integer reviewId) {
        var result = reviewQueryRepository.findByMemberIdAndReviewId(memberId, reviewId)
                .orElseThrow(ReviewNotFoundException::new);
        return ReviewResponse.from(result);
    }

    public List<ReviewResponse> getAllByMe(String memberId, Integer limit) {
        Pageable pageable = Pageable.ofSize(limit);
        return reviewQueryRepository.findAllByMemberId(memberId, pageable)
            .stream()
            .map(ReviewResponse::ofListItemAtMe)
            .collect(Collectors.toList());
    }

    public CursorPageDto<Integer, ReviewResponse> getAllByBeer(Integer beerId, Integer cursor, Integer limit) {
        return CursorPagingUtil.paging(
                pageable -> getBeers(beerId, cursor, pageable),
                ReviewResponse::getId,
                limit
        );
    }

    private List<ReviewResponse> getBeers(Integer beerId, Integer cursor, Pageable pageable) {
        var ids = Objects.isNull(cursor) ?
                reviewQueryRepository.findIdByBeerId(beerId, pageable) :
                reviewQueryRepository.findIdByBeerIdForCursor(beerId, cursor, pageable);
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        return reviewQueryRepository.findAllByIdIn(ids)
                .stream()
                .map(ReviewResponse::ofListItemAtBeer)
                .collect(Collectors.toList());
    }
}
