package com.beerair.core.review.infrastructure;

import com.beerair.core.common.util.NativeQueryReader;
import com.beerair.core.review.dto.query.FlavorDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FlavorRankRepositoryImpl implements FlavorRankRepository {
    // TODO 갯수 같이 반환하는거 뺴먹음!!
    private static final int LIMIT = 3;
    private static final String SQL =
                            "SELECT flavor as id, f.content " +
                            "FROM (" +
                                "SELECT beer_id, flavor1 as flavor FROM review " +
                                "UNION ALL " +
                                "SELECT beer_id, flavor2 as flavor FROM review " +
                                "UNION ALL " +
                                "SELECT beer_id, flavor3 as flavor FROM review " +
                            ") r" +
                            "INNER JOIN flavor f ON f.id = flavor " +
                            "GROUP BY beer_id, flavor HAVING flavor IS NOT NULL AND beer_id = :beerId " +
                            "ORDER BY COUNT(id) DESC";
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<FlavorDto> findTop3ByBeerId(String beerId) {
        List<?> result = createQuery(beerId).getResultList();
        return result.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private Query createQuery(String beerId) {
        var query = em.createNativeQuery(SQL);
        query.setParameter("beerId", beerId);
        query.setMaxResults(LIMIT);
        return query;
    }

    private FlavorDto convert(Object row) {
        var reader = new NativeQueryReader(row);
        return new FlavorDtoImpl(
                reader.getLong(0),
                reader.getString(1)
        );
    }

    @Getter
    @RequiredArgsConstructor
    public static class FlavorDtoImpl implements FlavorDto {
        private final Long id;
        private final String content;
    }
}