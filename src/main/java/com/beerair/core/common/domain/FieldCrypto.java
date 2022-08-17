package com.beerair.core.common.domain;

public interface FieldCrypto {
    String encrypt(String str);

    String decrypt(String str);
}
