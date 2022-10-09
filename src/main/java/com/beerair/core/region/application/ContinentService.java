package com.beerair.core.region.application;

import com.beerair.core.region.dto.response.ContinentResponse;
import com.beerair.core.region.dto.response.ContinentResponses;
import com.beerair.core.region.infrastructure.ContinentRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ContinentService {
    private final ContinentRepository continentRepository;

    public ContinentResponses getAll() {
        var continent = continentRepository.findAll()
                .stream()
                .map(ContinentResponse::from)
                .collect(Collectors.toList());

        return new ContinentResponses(continent);
    }

    public Boolean exists(Long continentId) {
        return continentRepository.existsById(continentId);
    }
}
