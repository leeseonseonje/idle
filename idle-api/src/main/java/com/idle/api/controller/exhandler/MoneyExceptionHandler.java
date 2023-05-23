package com.idle.api.controller.exhandler;

import com.idle.api.controller.ItemController;
import com.idle.api.controller.StoreController;
import com.idle.api.controller.query.ItemQueryController;
import com.idle.item.exception.NotWearItemException;
import com.idle.money.exception.ShortOfMoneyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice(assignableTypes = {ItemQueryController.class, ItemController.class, StoreController.class})
public class MoneyExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ShortOfMoneyException.class)
    public String shorOfMoney(ShortOfMoneyException e) {
        log.info(e.getMessage());
        return e.getMessage();
    }
}
