package com.oilerplatecode.bophelo.run.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RunNotFoundException.class)
    public ResponseEntity<RunErrorResponse> handleRunNotFoundException(
            RunNotFoundException notFoundException,
            HttpServletRequest request) {
        RunErrorResponse errorResponse = new RunErrorResponse(notFoundException.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
