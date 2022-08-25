package com.beerair.core.beer.infrastructure;

import com.beerair.core.beer.domain.BeerLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeerLikeRepository extends JpaRepository<BeerLike, Long> {
    @Query("DELETE FROM BeerLike bk WHERE bk.memberId = :memberId AND bk.beerId = :beerId")
    Long deleteByBeerIdAndMemberId(@Param("memberId") String memberId, @Param("beerId") String beerId);
}
