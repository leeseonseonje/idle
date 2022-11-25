package com.idle.item.exception;

public class SynthesisFailedException extends IllegalStateException {
    public SynthesisFailedException() {
        super();
    }

    public SynthesisFailedException(String s) {
        super(s);
    }

    public SynthesisFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SynthesisFailedException(Throwable cause) {
        super(cause);
    }
}
