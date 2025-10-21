package com.fpt.quiz_adapter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflict(Exception exception){
        return buildErrorResponse(exception,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(Exception exception){
        return buildErrorResponse(exception,HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception exception, HttpStatus status) {
        return ResponseEntity
            .status(status)
            .body(
                ErrorResponse.builder()
                    .success(false)
                    .message(exception.getMessage())
                    .code(status.value())
                    .build()
            );
    }
}
