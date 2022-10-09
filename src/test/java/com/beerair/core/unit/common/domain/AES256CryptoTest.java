package com.beerair.core.unit.common.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.beerair.core.common.domain.AES256Crypto;
import com.beerair.core.error.exception.common.PropertiesException;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AES256CryptoTest {
    @ValueSource(strings = {
            "아하하", "김재원", "darkant99@github.com", "@*$(U@()@!%(!#K%LSIOEPSITNSKTS"
    })
    @ParameterizedTest
    void crypt(String original) {
        var crypto = new AES256Crypto(Strings.repeat("F", 32));
        final String cipher = crypto.encrypt(original);

        assertThat(crypto.decrypt(cipher))
                .isEqualTo(original);
    }

    @Test
    void invalidKey() {
        final int KEY_LENGTH = 999;

        assertThatThrownBy(() -> new AES256Crypto(Strings.repeat("F", KEY_LENGTH)))
                .isInstanceOf(PropertiesException.class);
    }
}
