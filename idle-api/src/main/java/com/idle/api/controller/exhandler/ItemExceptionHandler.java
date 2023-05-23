package com.idle.api.controller.exhandler;

import com.idle.api.controller.ItemController;
import com.idle.api.controller.query.ItemQueryController;
import com.idle.item.exception.NotWearItemException;
import com.idle.item.exception.SynthesisFailedException;
import com.idle.weapon.exception.GradeUpFailedException;
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

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(GradeUpFailedException.class)
    public String gradeUpFailed(GradeUpFailedException e) {
        log.info(e.getMessage());
        return e.getMessage();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(SynthesisFailedException.class)
    public String synthesisFailed(SynthesisFailedException e) {
        log.info(e.getMessage());
        return e.getMessage();
    }
}
