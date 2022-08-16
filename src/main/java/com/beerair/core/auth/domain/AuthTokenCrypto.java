package com.beerair.core.auth.domain;

import com.beerair.core.auth.domain.AuthTokenAuthentication;

public interface AuthTokenCrypto {
    String encrypt(AuthTokenAuthentication authentication);

    AuthTokenAuthentication decrypt(String token);
}
