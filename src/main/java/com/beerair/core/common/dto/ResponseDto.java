package com.beerair.core.common.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class ResponseDto<T> implements Serializable {
    private T data;

    public ResponseDto() {
    }

    public ResponseDto(T data) {
        this.data = data;
    }


}
