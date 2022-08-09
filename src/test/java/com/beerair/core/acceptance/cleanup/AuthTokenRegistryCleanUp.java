package com.beerair.core.acceptance.cleanup;

import org.springframework.stereotype.Component;

import com.beerair.core.acceptance.auth.OAuth2TokenRegistry;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AuthTokenRegistryCleanUp implements CleanUp {
    @Override
    public void exec() {
        OAuth2TokenRegistry.clear();
    }
}
