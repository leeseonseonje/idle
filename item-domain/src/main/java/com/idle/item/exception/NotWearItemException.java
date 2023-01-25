package com.idle.item.exception;

public class NotWearItemException extends IllegalStateException {

    public NotWearItemException() {
        super();
    }

    public NotWearItemException(String s) {
        super(s);
    }

    public NotWearItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotWearItemException(Throwable cause) {
        super(cause);
    }
}
