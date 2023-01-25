package com.idle.api.controller.exhandler;

import com.idle.api.controller.ItemController;
import com.idle.api.controller.query.ItemQueryController;
import com.idle.item.exception.NotWearItemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice(assignableTypes = {ItemQueryController.class, ItemController.class})
public class ItemExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(NotWearItemException.class)
    public String notWearItem(NotWearItemException e) {
        log.info(e.getMessage());
        return e.getMessage();
    }
}
