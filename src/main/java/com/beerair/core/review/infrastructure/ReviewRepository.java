package com.beerair.core.review.infrastructure;

import com.beerair.core.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findTop1ByMemberIdOrderByCreatedAtDesc(String memberId);
}
