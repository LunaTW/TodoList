package com.luna.TodoList.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

import static java.lang.Boolean.FALSE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemoRequestDto {

    @NotNull(message = "~ Plan title cannot be none ~")
    private String message;

    private String tag="Others";

    private Boolean complete=FALSE;

    private Boolean publicity=FALSE;

}
