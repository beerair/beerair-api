package com.beerair.core.region.infrastructure;

import com.beerair.core.region.domain.Country;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    @Transactional(readOnly = true)
    List<Country> findByContinentId(Long continentId);

    @Transactional(readOnly = true)
    Optional<Country> findByEngName(String engName);
}
