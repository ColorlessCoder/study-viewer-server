package com.eastnetic.demo.controller;

import com.eastnetic.demo.dto.ErrorResponse;
import com.eastnetic.demo.util.MessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleConflict(Exception ex, WebRequest request) {
        if(ex instanceof MessageException) {
            return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(new ErrorResponse("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
