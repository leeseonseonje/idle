package com.idle.money.exception;

public class ShortOfMoneyException extends IllegalStateException {

    public ShortOfMoneyException() {
        super();
    }

    public ShortOfMoneyException(String s) {
        super(s);
    }

    public ShortOfMoneyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShortOfMoneyException(Throwable cause) {
        super(cause);
    }
}
