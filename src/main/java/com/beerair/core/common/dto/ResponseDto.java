package com.beerair.core.common.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@Getter
public class ResponseDto<T> implements Serializable {
    private final T data;

    public ResponseDto(T data) {
        this.data = data;
    }

    public static <T> ResponseEntity<ResponseDto<T>> ok(T data) {
        return ResponseEntity.ok(
            new ResponseDto<>(data)
        );
    }

    public static <T> ResponseEntity<ResponseDto<T>> created(T data) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto<>(data));
    }

    public static ResponseEntity<Void> noContent() {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
