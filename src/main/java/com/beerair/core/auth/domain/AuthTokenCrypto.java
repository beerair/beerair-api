package com.beerair.core.auth.domain;

public interface AuthTokenCrypto {
    AuthToken encrypt(AuthTokenAuthentication authentication);

    AuthTokenAuthentication decrypt(String token);
}
