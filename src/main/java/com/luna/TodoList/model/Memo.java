package com.luna.TodoList.model;

import lombok.*;

import javax.persistence.*;
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

    private Boolean publicity=FALSE;

    private LocalDate localDate_created=LocalDate.now();

    private LocalDate localDate_modified=LocalDate.now();

    @NotNull(message ="~UserId cannot be none~")
    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    @JoinColumn(name="userId")
    private User user;
}
