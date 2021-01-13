package com.luna.TodoList.dto;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

import static java.lang.Boolean.FALSE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoRequestDto {

    @NotNull(message = "~ Plan title cannot be none ~")
    private String message;

    private String tag="Others";

    private Boolean complete=FALSE;

    private Boolean publicity=FALSE;

    @NotNull(message ="~UserId cannot be none~")
    private Long userId;
}
