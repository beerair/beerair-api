package com.beerair.core.cucumber;

import com.beerair.core.common.dto.CursorPageDto;
import com.beerair.core.common.dto.ResponseDto;
import com.beerair.core.error.TestDebugException;
import com.beerair.core.fixture.Fixture;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.List;
import java.util.Map;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

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
    private static Object nextCursor;
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

    @SneakyThrows
    public static <T> List<T> getListInFirstClassBody(TypeReference<List<T>> typeReference) {
        var jsonNode = OBJECT_MAPPER.readTree(
            OBJECT_MAPPER.writeValueAsString(latestResponse.getBody())
        );
        return getListInFirstClassBody(jsonNode, typeReference);
    }

    @SneakyThrows
    private static <T> List<T> getListInFirstClassBody(JsonNode node, TypeReference<List<T>> typeReference) {

        if (node.isArray()) {
            return OBJECT_MAPPER.readValue(
                node.toString(), typeReference
            );
        }
        if (node.has("data")) {
            var dataNode = node.get("data");
            if (dataNode.isArray()) {
                return OBJECT_MAPPER.readValue(
                        dataNode.toString(), typeReference
                );
            }
        }
        if (node.size() > 1) {
            throw new TestDebugException("List를 찾을 수 없습니다.");
        }
        return getListInFirstClassBody(node.fields().next().getValue(), typeReference);
    }

    @SneakyThrows
    public static void selectByList(int index) {
        ResponseDto<List<?>> body = getBody(new TypeReference<>() {});
        Object selected = body.getData().get(index);
        ResponseDto<?> newBody = new ResponseDto<>(selected);

        new Fixture<>(latestResponse)
            .set("body", newBody);
    }

    public static void clearCursor() {
        nextCursor = null;
    }

    @SneakyThrows
    public static void saveCursor() {
        CursorPageDto<?, ?> body = getBody(new TypeReference<>() {});
        nextCursor = body.getNextCursor();
    }

    @SuppressWarnings("unchecked")
    public static <KEY> KEY getNextCursor() {
        return (KEY) nextCursor;
    }

    public static String getCookie() {
        return latestResponse.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
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
