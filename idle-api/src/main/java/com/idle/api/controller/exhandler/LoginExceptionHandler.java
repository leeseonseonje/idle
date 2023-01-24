package com.idle.api.controller.exhandler;

import com.idle.api.controller.LoginController;
import com.idle.oauth.exception.ExpiredAccessTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@RestControllerAdvice(assignableTypes = LoginController.class)
public class LoginExceptionHandler {

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(ExpiredAccessTokenException.class)
    public String expiredAccessToken(ExpiredAccessTokenException e) {
        log.info(e.getMessage());
        return e.getMessage();
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(IllegalStateException.class)
    public String notExistsMember(IllegalStateException e) {
        log.info(e.getMessage());
        return e.getMessage();
    }
}
