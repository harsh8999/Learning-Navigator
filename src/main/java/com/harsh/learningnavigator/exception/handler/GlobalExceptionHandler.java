package com.harsh.learningnavigator.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.harsh.learningnavigator.exception.dto.ExceptionApiResponse;
import com.harsh.learningnavigator.exception.exceptions.ResourceNotFoundException;

/**
 * Global exception handler for handling exceptions thrown within controller methods.
 * This class provides methods to handle specific types of exceptions and return appropriate HTTP responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * Handles ResourceNotFoundException and returns an HTTP 404 (Not Found) response.
     * 
     * @param ex the ResourceNotFoundException to handle
     * @return ResponseEntity containing an ExceptionApiResponse with the error message and HTTP status
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ExceptionApiResponse apiResponse = new ExceptionApiResponse(message);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles IllegalStateException and IllegalArgumentException and returns an HTTP 404 (Not Found) response.
     * 
     * @param ex The IllegalStateException or IllegalArgumentException to handle.
     * @return ResponseEntity containing an ExceptionApiResponse with the error message and HTTP status.
     */
    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class})
    public ResponseEntity<ExceptionApiResponse> illegalStateExceptionHandler(Exception ex){
        String message = ex.getMessage();
        ExceptionApiResponse apiResponse = new ExceptionApiResponse(message);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

}
