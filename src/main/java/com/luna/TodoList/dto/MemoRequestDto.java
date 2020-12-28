package com.luna.TodoList.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import static java.lang.Boolean.FALSE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoRequestDto {

    @NotNull
    private String message;

    private String tag="Others";

    private Boolean complete=FALSE;
}
