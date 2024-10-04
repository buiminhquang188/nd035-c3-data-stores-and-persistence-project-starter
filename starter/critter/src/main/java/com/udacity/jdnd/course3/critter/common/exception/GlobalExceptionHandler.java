package com.udacity.jdnd.course3.critter.common.exception;

import com.udacity.jdnd.course3.critter.common.exception.runtime.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(RuntimeException runtimeException, HttpServletRequest request) {
        return ResponseEntity.notFound()
                .build();
    }
}
