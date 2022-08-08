package com.beerair.core.acceptance.cleanup;

import org.springframework.stereotype.Component;

import com.beerair.core.acceptance.CucumberHttpResponseContext;

@Component
public class CucumberHttpResponseCleanUp implements CleanUp {
    @Override
    public void exec() {
        CucumberHttpResponseContext.set(null);
    }
}
