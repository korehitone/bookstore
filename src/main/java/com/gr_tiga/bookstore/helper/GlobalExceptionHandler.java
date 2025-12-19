package com.gr_tiga.bookstore.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gr_tiga.bookstore.helper.exception.DataNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(DataNotFoundException ex){
        ErrorResponse er = new ErrorResponse(
            "DATA_NOT_FOUND", 
            ex.getMessage(), 
            ex.getResourceName(), 
            ex.getIdValue());

            return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }
}
