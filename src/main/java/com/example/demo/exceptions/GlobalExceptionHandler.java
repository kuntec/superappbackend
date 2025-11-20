package com.example.demo.exceptions;

import com.example.demo.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ApiResponse handleException(Exception e) {
        return new ApiResponse(false, e.getMessage(), null);
    }

}
