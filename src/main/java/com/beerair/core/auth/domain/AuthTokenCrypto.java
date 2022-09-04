package com.beerair.core.auth.domain;

public interface AuthTokenCrypto {
    String encrypt(AuthTokenAuthentication authentication);

    AuthTokenAuthentication decrypt(String token);
}
