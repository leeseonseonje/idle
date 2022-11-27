package com.idle.weapon.exception;

public class GradeUpFailedException extends IllegalStateException {

    public GradeUpFailedException() {
        super("등급업 실패!");
    }

    public GradeUpFailedException(String s) {
        super(s);
    }

    public GradeUpFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public GradeUpFailedException(Throwable cause) {
        super(cause);
    }
}
