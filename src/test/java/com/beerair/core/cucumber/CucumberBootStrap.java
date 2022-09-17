package com.beerair.core.cucumber;

import com.beerair.core.cucumber.cleanup.CleanUp;
import com.beerair.core.cucumber.datasetup.DataSetup;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@CucumberContextConfiguration
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberBootStrap {
    @Autowired
    private List<CleanUp> cleanUps;
    @Autowired
    private List<DataSetup> setups;

    @Before
    public void cleanUp() {
        setups.forEach(DataSetup::exec);
        cleanUps.forEach(CleanUp::exec);
    }
}
