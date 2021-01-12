package com.luna.TodoList.dto;

import lombok.*;

import javax.persistence.Entity;
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
