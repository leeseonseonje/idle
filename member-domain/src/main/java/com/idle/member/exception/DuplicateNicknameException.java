package com.idle.member.exception;

public class DuplicateNicknameException extends IllegalStateException {

    public DuplicateNicknameException() {
        super();
    }

    public DuplicateNicknameException(String s) {
        super(s);
    }

    public DuplicateNicknameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateNicknameException(Throwable cause) {
        super(cause);
    }
}
