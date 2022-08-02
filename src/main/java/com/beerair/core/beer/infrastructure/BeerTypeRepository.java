package com.beerair.core.beer.infrastructure;

import com.beerair.core.beer.domain.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerTypeRepository extends JpaRepository<BeerType, Long> {
}
