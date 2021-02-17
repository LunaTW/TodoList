package com.luna.TodoList.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

import static java.lang.Boolean.FALSE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @Size(min=4, max=20, message = "username should between 4-20")
    @Pattern(regexp="[A-Za-z]*", message="English only")
    private String username;

    @NotNull
    private String password;

    private LocalDate DateOfBirth;

    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", message="Invalid email address")
    private String email;

    @Pattern(regexp = "[\\s]*[0-9]*[1-9]+", message="msg")
    private String phone;

    private Boolean admin=FALSE;
}
