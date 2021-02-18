package com.luna.TodoList.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static java.lang.Boolean.FALSE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    @NotNull
    private String username;

    private String password;

    private LocalDate DateOfBirth;

    private String email;

    private String phone;

    private Boolean admin = FALSE;
}
