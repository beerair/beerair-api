package com.beerair.core.error.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ErrorDto implements Serializable {
    private final String name;
    private final String message;
    private final String reason;

    public ErrorDto(ErrorMessage message, String reason) {
        this.name = message.name();
        this.message = message.getDescription();
        this.reason = reason;
    }

    public ErrorDto(ErrorMessage message) {
        this.name = message.name();
        this.message = message.getDescription();
        this.reason = "";
    }
}
