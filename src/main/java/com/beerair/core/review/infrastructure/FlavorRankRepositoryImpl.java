package com.beerair.core.review.infrastructure;

import com.beerair.core.common.util.NativeQueryReader;
import com.beerair.core.review.dto.query.FlavorRankDto;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class FlavorRankRepositoryImpl implements FlavorRankRepository {
    private static final String SQL =
                            "SELECT flavor as id, f.content, COUNT(id) as count " +
                            "FROM (" +
                                "SELECT beer_id, flavor1 as flavor FROM review " +
                                "UNION ALL " +
                                "SELECT beer_id, flavor2 as flavor FROM review " +
                                "UNION ALL " +
                                "SELECT beer_id, flavor3 as flavor FROM review " +
                            ") r " +
                            "INNER JOIN flavor f ON f.id = flavor " +
                            "GROUP BY beer_id, flavor HAVING flavor IS NOT NULL AND beer_id = :beerId " +
                            "ORDER BY COUNT(id) DESC";
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<FlavorRankDto> findRankByBeerId(Integer beerId, int limit) {
        List<?> result = createQuery(beerId, limit).getResultList();
        return result.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private Query createQuery(Integer beerId, int limit) {
        var query = em.createNativeQuery(SQL);
        query.setParameter("beerId", beerId);
        query.setMaxResults(limit);
        return query;
    }

    private FlavorRankDto convert(Object row) {
        var reader = new NativeQueryReader(row);
        return new FlavorRankDto(
                reader.getLong(0),
                reader.getString(1),
                reader.getLong(2)
        );
    }
}
