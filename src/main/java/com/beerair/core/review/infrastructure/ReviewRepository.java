package com.beerair.core.review.infrastructure;

import com.beerair.core.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByMemberIdOrderByIdDesc(Long memberId);
}
