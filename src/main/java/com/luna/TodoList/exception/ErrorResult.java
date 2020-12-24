package com.luna.TodoList.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResult {
    private Integer status;
    private String error;
    private String message;
}