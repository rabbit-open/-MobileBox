package com.hualala.domain.exception;

public class CreateUseCaseFailedException extends RuntimeException {
    public CreateUseCaseFailedException(Throwable cause) {
        super(cause);
    }

    public CreateUseCaseFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
