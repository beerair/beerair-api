package com.beerair.core.common.domain;

import com.beerair.core.error.exception.common.PropertiesException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

public class AES256Crypto implements Crypto {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    private final SecretKeySpec secureKeySpec;
    private final IvParameterSpec lvSpec;
    private Cipher enCipher;
    private Cipher deCipher;

    public AES256Crypto(String secretKey) {
        String lv = secretKey.substring(0, 16);
        this.lvSpec = new IvParameterSpec(lv.getBytes());
        this.secureKeySpec = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);

        try {
            this.enCipher = Cipher.getInstance(TRANSFORMATION);
            this.enCipher.init(Cipher.ENCRYPT_MODE, secureKeySpec, lvSpec);
            this.deCipher = Cipher.getInstance(TRANSFORMATION);
            this.deCipher.init(Cipher.DECRYPT_MODE, secureKeySpec, lvSpec);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException e) {
            throw new PropertiesException("AES256 인스턴스 생성 도중 문제가 발생 했습니다. 설정 값을 확인 해주세요.");
        }
    }

    @SneakyThrows
    public String encrypt(String str) {
        byte[] decrypted = enCipher.doFinal(str.getBytes(UTF_8));
        return Base64.getEncoder()
                .encodeToString(decrypted);
    }

    @SneakyThrows
    public String decrypt(String str) {
        byte[] encrypted = Base64.getDecoder()
                .decode(str);
        return new String(
                deCipher.doFinal(encrypted), UTF_8
        );
    }
}
