package com.luna.TodoList.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

import static java.lang.Boolean.FALSE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @NotNull
    private Long userId;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private LocalDate DateOfBirth;

    private String email;

    private String phone;

    private Boolean admin=FALSE;
}
