package com.beerair.core.beer.infrastructure;

import com.beerair.core.beer.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long>, JpaSpecificationExecutor<Beer> {
    @Transactional(readOnly = true)
    Boolean findByKorNameOrEngName(String korName, String engName);
}
