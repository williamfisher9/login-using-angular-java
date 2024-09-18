package com.apps.security.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class UserControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException exc){
        return new ResponseEntity<>("ERROR", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exc) {
        Map<String, Object> errorsMap = new HashMap<>();

        List<String> errorsList = exc.getBindingResult().getFieldErrors().stream()
                .map(field -> field.getDefaultMessage()).toList();

        errorsMap.put("errors", errorsList);

        return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
    }
}
