package com.YangKang.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handlerEntityNotFound(EntityNotFoundException exception){
        return ResponseEntity.status(404).body("Entity Not Found Exception");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handlerEntityNotFound(UsernameNotFoundException exception){
        return ResponseEntity.status(401).body("Account does is not Exists");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerException(Exception exception){
        return ResponseEntity.status(500).body("Unkown Error!!!");
    }

}
