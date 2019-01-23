package com.hualala.data.exception;

/**
 * Created by denglijun on 2017/11/30.
 */

public class CreateUseCaseFailedException extends RuntimeException {
    public CreateUseCaseFailedException(Throwable cause) {
        super(cause);
    }

    public CreateUseCaseFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
