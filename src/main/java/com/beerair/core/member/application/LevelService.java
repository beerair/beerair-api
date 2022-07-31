package com.beerair.core.member.application;

import com.beerair.core.member.infrastructure.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LevelService {
    private final LevelRepository levelRepository;
}
