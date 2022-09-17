package com.beerair.core.cucumber.cleanup;

import org.springframework.stereotype.Component;

import com.beerair.core.cucumber.CucumberHttpResponseContext;

@Component
public class CucumberHttpResponseCleanUp implements CleanUp {
    @Override
    public void exec() {
        CucumberHttpResponseContext.set(null);
    }
}
