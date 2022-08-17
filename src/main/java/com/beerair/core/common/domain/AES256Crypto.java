package com.beerair.core.common.domain;

import com.beerair.core.error.exception.common.PropertiesException;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class AES256Crypto implements FieldCrypto {
    private static final String PROPERTY_KEY = "${fieldCrypto.aes256.secretKey}";
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    private final SecretKeySpec secureKeySpec;
    private final IvParameterSpec lvSpec;
    private Cipher enCipher;
    private Cipher deCipher;

    public AES256Crypto(@Value(PROPERTY_KEY) String secretKey) {
        verifyKey(secretKey);

        String lv = secretKey.substring(0, 16);
        this.lvSpec = new IvParameterSpec(lv.getBytes());
        this.secureKeySpec = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
    }

    private void verifyKey(String secretKey) {
        if (Objects.isNull(secretKey) || secretKey.getBytes().length != 32) {
            throw new PropertiesException(PROPERTY_KEY + " 설정 값이 잘못 되었습니다. (32 바이트의 키를 입력 해주세요.)");
        }
    }

    @SneakyThrows
    public String encrypt(String str) {
        if (Objects.isNull(enCipher)) {
            this.enCipher = Cipher.getInstance(TRANSFORMATION);
            this.enCipher.init(Cipher.ENCRYPT_MODE, secureKeySpec, lvSpec);
        }
        byte[] decrypted = enCipher.doFinal(str.getBytes(UTF_8));
        return Base64.getEncoder()
                .encodeToString(decrypted);
    }

    @SneakyThrows
    public String decrypt(String str) {
        if (Objects.isNull(deCipher)) {
            this.deCipher = Cipher.getInstance(TRANSFORMATION);
            this.deCipher.init(Cipher.DECRYPT_MODE, secureKeySpec, lvSpec);
        }
        byte[] encrypted = Base64.getDecoder()
                .decode(str);
        return new String(
                deCipher.doFinal(encrypted), UTF_8
        );
    }
}
