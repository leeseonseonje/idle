package com.idle.shop.exception;

public class BoomException extends IllegalStateException {

    public BoomException() {
        super();
    }

    public BoomException(String s) {
        super(s);
    }

    public BoomException(String message, Throwable cause) {
        super(message, cause);
    }

    public BoomException(Throwable cause) {
        super(cause);
    }
}
