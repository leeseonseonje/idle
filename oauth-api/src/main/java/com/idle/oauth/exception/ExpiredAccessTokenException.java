package com.idle.oauth.exception;

public class ExpiredAccessTokenException extends IllegalStateException {

    public ExpiredAccessTokenException() {
        super();
    }

    public ExpiredAccessTokenException(String s) {
        super(s);
    }

    public ExpiredAccessTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpiredAccessTokenException(Throwable cause) {
        super(cause);
    }
}
