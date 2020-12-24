package com.luna.TodoList.exception;

public class MemoNotFoundException extends RuntimeException {
    public MemoNotFoundException(String message) {
        super(message);
    }
}