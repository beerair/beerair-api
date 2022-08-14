package com.beerair.core.region.infrastructure;

import com.beerair.core.region.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @Transactional(readOnly = true)
    List<Country> findByContinentId(Long continentId);
}
