package com.beerair.core.beer.infrastructure;

import com.beerair.core.beer.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {
}
