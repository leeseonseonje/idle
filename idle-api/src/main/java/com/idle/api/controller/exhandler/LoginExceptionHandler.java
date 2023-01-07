package com.idle.api.controller.exhandler;

import com.idle.api.controller.LoginController;
import com.idle.oauth.exception.ExpiredAccessTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice(assignableTypes = LoginController.class)
public class LoginExceptionHandler {

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
