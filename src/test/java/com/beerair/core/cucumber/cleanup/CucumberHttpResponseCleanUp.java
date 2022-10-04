package com.beerair.core.cucumber.cleanup;

import com.beerair.core.cucumber.CucumberHttpResponseContext;
import org.springframework.stereotype.Component;

@Component
public class CucumberHttpResponseCleanUp implements CleanUp {
    @Override
    public void exec() {
        CucumberHttpResponseContext.set(null);
    }
}
