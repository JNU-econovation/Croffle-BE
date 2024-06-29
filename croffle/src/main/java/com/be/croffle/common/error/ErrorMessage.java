package com.be.croffle.common.error;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    FORBIDDEN("Forbidden"),
    UNAUTHORIZED("Unauthorized");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}

