package com.beerair.core.review.infrastructure;

import com.beerair.core.review.domain.Review;
import com.beerair.core.review.dto.query.ReviewDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewQueryRepository extends JpaRepository<Review, String> {
    @Query("SELECT r as review, " +
            "b as beer, " +
            "dc as departuresCountry, " +
            "ac as arrivalCountry, " +
            "f1 as flavor1, " +
            "f2 as flavor2, " +
            "f3 as flavor3 " +
            "FROM Review r " +
            "INNER JOIN Beer b ON b.id = r.beerId AND b.deletedAt IS NULL " +
            "INNER JOIN Country dc ON dc.id = r.route.departureCountryId " +
            "INNER JOIN Country ac ON ac.id = r.route.arrivalCountryId " +
            "LEFT OUTER JOIN Flavor f1 ON f1.id = r.flavorIds.flavor1 " +
            "LEFT OUTER JOIN Flavor f2 ON f2.id = r.flavorIds.flavor2 " +
            "LEFT OUTER JOIN Flavor f3 ON f3.id = r.flavorIds.flavor3 " +
            "WHERE r.memberId = :memberId AND r.id = :reviewId AND r.deletedAt IS NULL")
    Optional<ReviewDto> findByMemberIdAndReviewId(@Param("memberId") String memberId, @Param("reviewId") String reviewId);

    @Query("SELECT r as review, " +
            "b as beer, " +
            "dc as departuresCountry, " +
            "ac as arrivalCountry, " +
            "f1 as flavor1, " +
            "f2 as flavor2, " +
            "f3 as flavor3 " +
            "FROM Review r " +
            "INNER JOIN Beer b ON b.id = r.beerId AND b.deletedAt IS NULL " +
            "INNER JOIN Country dc ON dc.id = r.route.departureCountryId " +
            "INNER JOIN Country ac ON ac.id = r.route.arrivalCountryId " +
            "LEFT OUTER JOIN Flavor f1 ON f1.id = r.flavorIds.flavor1 " +
            "LEFT OUTER JOIN Flavor f2 ON f2.id = r.flavorIds.flavor2 " +
            "LEFT OUTER JOIN Flavor f3 ON f3.id = r.flavorIds.flavor3 " +
            "WHERE r.memberId = :memberId AND r.deletedAt IS NULL")
    List<ReviewDto> findAllByMemberIdWithDetail(@Param("memberId") String memberId, Pageable pageable);

    @Query("SELECT r as review, " +
        "b as beer, " +
        "dc as departuresCountry, " +
        "ac as arrivalCountry " +
        "FROM Review r " +
        "INNER JOIN Beer b ON b.id = r.beerId AND b.deletedAt IS NULL " +
        "INNER JOIN Country dc ON dc.id = r.route.departureCountryId " +
        "INNER JOIN Country ac ON ac.id = r.route.arrivalCountryId " +
        "WHERE r.memberId = :memberId AND r.deletedAt IS NULL " +
        "ORDER BY r.createdAt DESC")
    List<ReviewDto> findAllByMemberId(@Param("memberId") String memberId, Pageable pageable);

    @Query("SELECT r as review, " +
            "f1 as flavor1, " +
            "f2 as flavor2, " +
            "f3 as flavor3, " +
            "m as member " +
            "FROM Review r " +
            "INNER JOIN Member m ON m.id = r.memberId AND m.deletedAt IS NULL " +
            "LEFT OUTER JOIN Flavor f1 ON f1.id = r.flavorIds.flavor1 " +
            "LEFT OUTER JOIN Flavor f2 ON f2.id = r.flavorIds.flavor2 " +
            "LEFT OUTER JOIN Flavor f3 ON f3.id = r.flavorIds.flavor3 " +
            "WHERE r.beerId = :beerId AND r.deletedAt IS NULL " +
            "ORDER BY r.createdAt DESC")
    List<ReviewDto> findAllByBeerId(@Param("beerId") String beerId);

    @Query("SELECT r as review, " +
            "f1 as flavor1, " +
            "f2 as flavor2, " +
            "f3 as flavor3, " +
            "m as member " +
            "FROM Review r " +
            "INNER JOIN Member m ON m.id = r.memberId AND m.deletedAt IS NULL " +
            "LEFT OUTER JOIN Flavor f1 ON f1.id = r.flavorIds.flavor1 " +
            "LEFT OUTER JOIN Flavor f2 ON f2.id = r.flavorIds.flavor2 " +
            "LEFT OUTER JOIN Flavor f3 ON f3.id = r.flavorIds.flavor3 " +
            "WHERE r.beerId = :beerId AND r.deletedAt IS NULL " +
            "ORDER BY r.createdAt DESC")
    List<ReviewDto> findAllByBeerId(@Param("beerId") String beerId, Pageable pageable);
}
