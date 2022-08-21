package com.beerair.core.acceptance.cleanup;

import com.beerair.core.member.domain.Level;
import com.beerair.core.member.infrastructure.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class LevelCleanUp implements CleanUp {
    @Autowired
    private LevelRepository levelRepository;

    @Transactional
    @Override
    public void exec() {
        List<Level> levels = List.of(
                Level.builder()
                        .imageUrl("https://beerair-service.s3.ap-northeast-2.amazonaws.com/MEMBER/LEVEL/1.png")
                        .exp(0)
                        .tier(1)
                        .build(),
                Level.builder()
                        .imageUrl("https://beerair-service.s3.ap-northeast-2.amazonaws.com/MEMBER/LEVEL/2.png")
                        .exp(1)
                        .tier(1)
                        .build(),
                Level.builder()
                        .imageUrl("https://beerair-service.s3.ap-northeast-2.amazonaws.com/MEMBER/LEVEL/3.png")
                        .exp(5)
                        .tier(3)
                        .build(),
                Level.builder()
                        .imageUrl("https://beerair-service.s3.ap-northeast-2.amazonaws.com/MEMBER/LEVEL/4.png")
                        .exp(12)
                        .tier(4)
                        .build(),
                Level.builder()
                        .imageUrl("https://beerair-service.s3.ap-northeast-2.amazonaws.com/MEMBER/LEVEL/5.png")
                        .exp(20)
                        .tier(5)
                        .build()
        );
        levelRepository.deleteAll();
        levelRepository.saveAllAndFlush(levels);
    }
}
