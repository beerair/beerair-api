package com.beerair.core.common.domain;

public interface Crypto {
    String encrypt(String str);

    String decrypt(String str);
}
