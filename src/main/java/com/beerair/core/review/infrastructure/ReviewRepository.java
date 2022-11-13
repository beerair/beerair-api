package com.beerair.core.review.infrastructure;

import com.beerair.core.review.domain.Review;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    @Query("SELECT r FROM Review r WHERE r.memberId = :memberId AND r.deletedAt IS NULL")
    List<Review> findAllByMemberId(@Param("memberId") String memberId, Pageable pageable);

    default Optional<Review> findLatestByMemberId(String memberId) {
        var result = findAllByMemberId(
                memberId,
                PageRequest.of(0, 1, Sort.by("createdAt").descending())
        );
        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result.get(0));
    }

    @Query("SELECT r as review " +
            "FROM Review r " +
            "WHERE r.memberId = :memberId " +
            "AND (r.id = :reviewId OR r.previousId = :reviewId) " +
            "AND r.deletedAt IS NULL " +
            "ORDER BY r.createdAt")
    List<Review> findAllByIdAndPreviousIdAndMemberId(@Param("memberId") String memberId, @Param("reviewId") String reviewId);

    /** 리뷰 목록이 작업될때까지 테스트에서 임시로 사용할 메서드 입니다. */
    @Query("SELECT r as review " +
            "FROM Review r " +
            "WHERE r.beerId = :beerId " +
            "AND r.deletedAt IS NULL")
    Optional<Review> findByBeerId(@Param("beerId") Integer beerId);
}
