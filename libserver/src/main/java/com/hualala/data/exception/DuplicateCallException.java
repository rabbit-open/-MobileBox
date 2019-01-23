package com.hualala.data.exception;

/**
 * Created by denglijun on 2018/5/13 13:35
 * description:
 */
public class DuplicateCallException extends RuntimeException {
    public DuplicateCallException() {
        super();
    }

    public DuplicateCallException(Throwable cause) {
        super(cause);
    }
}
