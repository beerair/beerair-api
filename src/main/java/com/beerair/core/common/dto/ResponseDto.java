package com.beerair.core.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseDto<T> implements Serializable {
    private T data;

    public ResponseDto() {
    }

    public ResponseDto(T data) {
        this.data = data;
    }
}
