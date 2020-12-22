package com.luna.TodoList.dto;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoRequestDto {
//    @NotNull
//    private Long id;

    @NotNull
    private String message;

    private String tag="Others";
}
