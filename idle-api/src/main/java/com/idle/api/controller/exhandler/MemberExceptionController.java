package com.idle.api.controller.exhandler;

import com.idle.api.controller.MemberController;
import com.idle.member.exception.DuplicateNicknameException;
import com.idle.oauth.exception.ExpiredAccessTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice(assignableTypes = MemberController.class)
public class MemberExceptionController {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(DuplicateNicknameException.class)
    public String duplicationNickname(DuplicateNicknameException e) {
        return e.getMessage();
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(ExpiredAccessTokenException.class)
    public String expiredAccessToken(ExpiredAccessTokenException e) {
        return e.getMessage();
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(IllegalStateException.class)
    public String notExistsMember(IllegalStateException e) {
        return e.getMessage();
    }
}
