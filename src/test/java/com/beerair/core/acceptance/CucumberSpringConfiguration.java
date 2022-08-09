package com.beerair.core.acceptance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com.beerair.core.acceptance.cleanup.CleanUp;
import com.beerair.core.acceptance.config.TestBeanConfig;

import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;

@Import(TestBeanConfig.class)
@CucumberContextConfiguration
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSpringConfiguration {
    @Autowired
    private List<CleanUp> cleanUps;

    @Before
    public void setUp() {
        cleanUps.forEach(CleanUp::exec);
    }
}
