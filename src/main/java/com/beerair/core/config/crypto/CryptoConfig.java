package com.beerair.core.config.crypto;

import com.beerair.core.common.domain.AES256Crypto;
import com.beerair.core.common.domain.Crypto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"local", "prod", "staging"})
@Configuration
public class CryptoConfig {
    @Bean
    @ConditionalOnMissingBean(Crypto.class)
    public Crypto crypto(@Value("${fieldCrypto.aes256.secretKey}") String secretKey) {
        return new AES256Crypto(secretKey);
    }
}
