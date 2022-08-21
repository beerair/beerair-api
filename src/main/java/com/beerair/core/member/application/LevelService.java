package com.beerair.core.member.application;

import com.beerair.core.member.dto.LevelResponse;
import com.beerair.core.member.infrastructure.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class LevelService {
    private final LevelRepository levelRepository;

    @Transactional(readOnly = true)
    public List<LevelResponse> getAll() {
        return levelRepository.findAll()
                .stream()
                .map(LevelResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LevelResponse getIdByExp(int exp) {
        return LevelResponse.from(
                levelRepository.findTop1ByExpGreaterThanOrderByTierDesc(exp)
        );
    }
}
