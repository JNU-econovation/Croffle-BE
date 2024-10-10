package com.be.croffle.common.error;

import com.be.croffle.common.error.ErrorMessage;
import lombok.Getter;

@Getter
public class UnAuthorizedException extends RuntimeException{
    private final ErrorMessage errorMessage;

    public UnAuthorizedException(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}