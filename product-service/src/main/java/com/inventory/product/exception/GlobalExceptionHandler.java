package com.inventory.product.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFountException.class)
    public ErrorResponse handleProductNotFountException(ProductNotFountException ex, HttpServletRequest request) {

        return ErrorResponse.builder().status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage()).timeStamp(LocalDateTime.now()).path(request.getRequestURI()).build();

    }
}
