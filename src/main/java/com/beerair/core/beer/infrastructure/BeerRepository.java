package com.beerair.core.beer.infrastructure;

import com.beerair.core.beer.domain.Beer;
import com.beerair.core.beer.domain.vo.BeerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long>, JpaSpecificationExecutor<Beer> {

	@Query(value = "SELECT b as beer, " +
	               "c as country, bt as beerType, false as isLiked " +
	               "FROM Beer b " +
	               "JOIN BeerType bt on b.typeId = bt.id " +
	               "JOIN Country c on b.countryId = c.id " +
	               "LEFT OUTER JOIN BeerLike bl on b.id = bl.beerId " +
	               "WHERE b.id = :beerId and b.deletedAt is null")
	BeerDto findByIdWithTypeAndCountry(@Param("beerId") Long beerId);

	@Query(value = "SELECT b as beer, " +
	               "c as country, bt as beerType, (bl is not null) as isLiked " +
	               "FROM Beer b " +
	               "JOIN BeerType bt on b.typeId = bt.id " +
	               "JOIN Country c on b.countryId = c.id " +
	               "LEFT OUTER JOIN BeerLike bl on b.id = bl.beerId and bl.memberId = :memberId " +
	               "WHERE b.id = :beerId and b.deletedAt is null")
	BeerDto findByIdWithTypeAndCountry(@Param("beerId") Long beerId, @Param("memberId") Long memberId);
}
