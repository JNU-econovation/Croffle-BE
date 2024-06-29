package com.be.croffle.common.error;

import com.be.croffle.common.error.ErrorMessage;
import lombok.Getter;

@Getter
public class ForbiddenException extends RuntimeException{
    private final ErrorMessage errorMessage;

    public ForbiddenException(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}