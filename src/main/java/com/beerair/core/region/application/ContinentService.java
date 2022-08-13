package com.beerair.core.region.application;

import com.beerair.core.region.domain.vo.rs.ContinentResponse;
import com.beerair.core.region.domain.vo.rs.ContinentResponses;
import com.beerair.core.region.infrastructure.ContinentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContinentService {
    private final ContinentRepository continentRepository;

    public ContinentResponses getAll() {
        return ContinentResponses.from(
                continentRepository.findAll()
                .stream()
                .map(ContinentResponse::from)
                .collect(Collectors.toList()));
    }
}
