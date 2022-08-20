package com.beerair.core.auth.domain;

import java.util.Date;

public interface AuthTokenCrypto {
    String encrypt(AuthTokenAuthentication authentication);

    AuthTokenAuthentication decrypt(String token);
}
