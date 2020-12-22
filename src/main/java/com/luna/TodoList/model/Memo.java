package com.luna.TodoList.model;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static java.lang.Boolean.FALSE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Memo {
    @NotNull
    private Long id;

    @NotNull
    private String message;

    private String tag="Others";

    private Boolean complete=FALSE;

    private LocalDate localDate_created=LocalDate.now();

    private LocalDate localDate_modified=LocalDate.now();
}
