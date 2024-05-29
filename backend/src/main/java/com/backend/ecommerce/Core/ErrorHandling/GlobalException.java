package com.backend.ecommerce.Core.ErrorHandling;

import com.backend.ecommerce.Core.ErrorHandling.RuntimeExceptions.UserFailedException;
import com.backend.ecommerce.Core.ErrorHandling.RuntimeExceptions.CustomNotFoundException;
import com.backend.ecommerce.Core.ErrorHandling.RuntimeExceptions.CustomValidationException;
import com.backend.ecommerce.Core.ErrorHandling.RuntimeExceptions.TokenCreationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(CustomNotFoundException ex){
        log.error("Not Found Exception: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<ErrorResponse> handleCustomValidationException(CustomValidationException ex){
        log.error("Validation Exception: field: {}, message: {}", ex.getField(), ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getField() + ": " + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenCreationException.class)
    public ResponseEntity<ErrorResponse> handleTokenException(TokenCreationException ex){
        log.error("Token Exception: message: {}" , ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserFailedException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(UserFailedException ex){
        log.error("Authentication Exception: message: {}" , ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(ex.getStatusCode(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
      log.error("Generic Exception: " + ex);
      ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An Unexpected Error Occurs");
      return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
