package com.beerair.core.cucumber.cleanup;

import com.beerair.core.review.domain.Review;
import com.beerair.core.review.infrastructure.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ReviewCleanUp implements CleanUp {
    @Autowired
    private ReviewRepository reviewRepository;
    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void exec() {
        reviewRepository.deleteAll();
        em.createNativeQuery("ALTER TABLE review ALTER id RESTART WITH 1;")
                .executeUpdate();
    }
}
