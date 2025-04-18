package com.hello.demo.spring.mvc.hello.xml.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultExceptionHandler {


    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> exceptionHandler() {
        return ResponseEntity.badRequest().body("参数错误");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> defaultHandler() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("服务器内部错误");
    }
}
