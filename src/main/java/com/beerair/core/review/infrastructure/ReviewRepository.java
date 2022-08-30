package com.beerair.core.review.infrastructure;

import com.beerair.core.review.domain.Review;
import com.beerair.core.review.domain.vo.Route;
import com.beerair.core.review.dto.query.ReviewDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    @Transactional(readOnly = true)
    @Query("SELECT r.route FROM Review r WHERE r.memberId = :memberId AND r.deletedAt IS NULL")
    List<Route> findAllRouteByMemberId(String memberId, Pageable pageable);

    default Optional<Route> findLatestRouteByMemberId(String memberId) {
        var result = findAllRouteByMemberId(
                memberId,
                PageRequest.of(0, 1, Sort.by("createdAt").descending())
        );
        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result.get(0));
    }

    @Transactional(readOnly = true)
    @Query("SELECT r as review, " +
            "b as beer, " +
            "dc as departuresCountry, " +
            "ac as arrivalCountry, " +
            "f1 as flavor1, " +
            "f2 as flavor2, " +
            "f3 as flavor3 " +
            "FROM Review r " +
            "INNER JOIN Beer b ON b.id = r.beerId AND b.id = :beerId AND b.deletedAt IS NULL " +
            "INNER JOIN Country dc ON dc.id = r.route.departureCountryId " +
            "INNER JOIN Country ac ON ac.id = r.route.arrivalCountryId " +
            "LEFT OUTER JOIN Flavor f1 ON f1.id = r.flavorIds.flavor1 " +
            "LEFT OUTER JOIN Flavor f2 ON f2.id = r.flavorIds.flavor2 " +
            "LEFT OUTER JOIN Flavor f3 ON f3.id = r.flavorIds.flavor3 " +
            "WHERE r.memberId = :memberId AND r.deletedAt IS NULL")
    Optional<ReviewDto> findByMemberIdAndBeerId(@Param("memberId") String memberId, @Param("beerId") String beerId);
}
