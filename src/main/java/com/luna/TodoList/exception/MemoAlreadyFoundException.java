package com.luna.TodoList.exception;

public class MemoAlreadyFoundException extends RuntimeException {
    public MemoAlreadyFoundException(String message) {
        super(message);
    }
}
