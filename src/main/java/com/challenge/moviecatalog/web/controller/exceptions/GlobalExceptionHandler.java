package com.challenge.moviecatalog.web.controller.exceptions;

import io.jsonwebtoken.JwtException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleException(JwtException ex) {
        return createErrorMessage(HttpStatus.UNAUTHORIZED, "Unauthorized access");
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleException(JdbcSQLIntegrityConstraintViolationException ex) {
        return createErrorMessage(HttpStatus.BAD_GATEWAY, "Integrity constraint violation");
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleException(BadCredentialsException ex) {
        return createErrorMessage(HttpStatus.BAD_REQUEST, "Bad credentials");
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleException(HttpMessageNotReadableException ex) {
        return createErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleException(MethodArgumentNotValidException ex) {
        return createErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleException(UsernameNotFoundException ex) {
        return createErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
    }



    private ResponseEntity<ErrorMessage> createErrorMessage(HttpStatus httpStatus, String errorCode) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(errorCode);
        errorMessage.setStatus(httpStatus);
        return ResponseEntity.status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorMessage);
    }
}
