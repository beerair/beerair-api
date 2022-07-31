package com.beerair.core.region.infrastructure;

import com.beerair.core.region.domain.Continent;
import com.beerair.core.region.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Long> {
}
