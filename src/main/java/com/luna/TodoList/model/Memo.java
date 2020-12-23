package com.luna.TodoList.model;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static java.lang.Boolean.FALSE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "~ Plan title cannot be none ~")
    private String message;

    private String tag="Others";

    private Boolean complete=FALSE;

    private LocalDate localDate_created=LocalDate.now();

    private LocalDate localDate_modified=LocalDate.now();
}
