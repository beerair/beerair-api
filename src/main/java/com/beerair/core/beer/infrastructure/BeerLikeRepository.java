package com.beerair.core.beer.infrastructure;

import com.beerair.core.beer.domain.BeerLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerLikeRepository extends JpaRepository<BeerLike, Long> {
}
