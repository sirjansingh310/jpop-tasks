package com.epam.bookservice.controller;

import com.epam.bookservice.model.response.ApiError;
import exception.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
@Slf4j
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest webRequest){
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        log.error(ex.getMessage());
        return new ResponseEntity<>(new ApiError(ex.getMessage(), httpStatus), httpStatus);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException ex, WebRequest webRequest){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        log.error(ex.getMessage());
        return new ResponseEntity<>(new ApiError(ex.getMessage(), httpStatus), httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        for(FieldError fieldError: ex.getBindingResult().getFieldErrors()){
            stringBuilder.append(fieldError.getDefaultMessage()).append(System.lineSeparator());
        }
        String message = stringBuilder.toString();
        log.error(message);
        return new ResponseEntity<>(new ApiError(message, status), status);
    }
}
