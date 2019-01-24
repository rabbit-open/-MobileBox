package com.hualala.domain.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorCodeException extends RuntimeException {

    public String errorCode;
    public String errorMsg;

    public ErrorCodeException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ErrorCodeException(String message, Throwable cause, String errorCode, String errorMsg) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
