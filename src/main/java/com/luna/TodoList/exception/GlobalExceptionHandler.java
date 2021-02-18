package com.luna.TodoList.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handle(MethodArgumentNotValidException exception) {
        String message = Objects.requireNonNull(exception.getBindingResult().getFieldError().getDefaultMessage());
        ErrorResult errorResult = new ErrorResult(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResult> handle(NotFoundException exception) {
        String message = exception.getMessage();
        ErrorResult errorResult = new ErrorResult(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }

    @ExceptionHandler(IncorrectInformationException.class)
    public ResponseEntity<ErrorResult> handle(IncorrectInformationException exception) {
        String message = exception.getMessage();
        ErrorResult errorResult = new ErrorResult(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResult> handle(UserAlreadyExistException exception) {
        String message = exception.getMessage();
        ErrorResult errorResult = new ErrorResult(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(MemoAlreadyFoundException.class)
    public ResponseEntity<ErrorResult> handle(MemoAlreadyFoundException exception) {
        String message = exception.getMessage();
        ErrorResult errorResult = new ErrorResult(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(NoAccessException.class)
    public ResponseEntity<ErrorResult> handle(NoAccessException exception) {
        String message = exception.getMessage();
        ErrorResult errorResult = new ErrorResult(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
}
