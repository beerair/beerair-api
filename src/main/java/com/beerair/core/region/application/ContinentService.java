package com.beerair.core.region.application;

import com.beerair.core.region.dto.response.ContinentResponse;
import com.beerair.core.region.infrastructure.ContinentRepository;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ContinentService {
    private final ContinentRepository continentRepository;

    public List<ContinentResponse> getAll() {
        return continentRepository.findAll()
                .stream()
                .map(ContinentResponse::from)
                .collect(Collectors.toList());
    }

    public Boolean exists(Long continentId) {
        return continentRepository.existsById(continentId);
    }
}
