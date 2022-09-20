package com.beerair.core.member.application;

import com.beerair.core.member.domain.Levels;
import com.beerair.core.member.dto.response.LevelResponse;
import com.beerair.core.member.infrastructure.LevelRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // TODO :: Cache
    @Transactional(readOnly = true)
    public Levels getLevels() {
        return levelRepository.findAll()
            .stream()
            .collect(Collectors.collectingAndThen(
                Collectors.toList(), Levels::new
            ));
    }
}
