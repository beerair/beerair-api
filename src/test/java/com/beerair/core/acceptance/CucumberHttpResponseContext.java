package com.beerair.core.acceptance;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

/**
 * @author 김재원
 *
 * Cucumber 테스트 중 ResponseEntity를 저장하기 위한 객체 입니다.
 *
 * When 에서 전송한 요청을
 * Then 에서 검증하기 위해 사용 됩니다.
 * */
@UtilityClass
public class CucumberHttpResponseContext {
    private static final ObjectMapper OBJECT_MAPPER;
    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
    }

    private static ResponseEntity<?> latestResponse;

    public static void set(ResponseEntity<?> entity) {
        latestResponse = entity;
    }

    @SneakyThrows
    public static <T> T getBody(TypeReference<T> typeReference) {
        var json = OBJECT_MAPPER.readTree(
            OBJECT_MAPPER.writeValueAsString(latestResponse.getBody())
        );
        return OBJECT_MAPPER.readValue(
            json.toString(), typeReference
        );
    }

    public static boolean is2XX() {
        return getStatusCodeRange() == 2;
    }

    public static boolean is3XX() {
        return getStatusCodeRange() == 3;
    }

    public static boolean is4XX() {
        return getStatusCodeRange() == 4;
    }

    private static int getStatusCodeRange() {
        return latestResponse.getStatusCodeValue() / 100;
    }
}
