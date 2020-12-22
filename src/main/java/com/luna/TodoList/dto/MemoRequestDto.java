package com.luna.TodoList.dto;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
