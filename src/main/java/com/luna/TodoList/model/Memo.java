package com.luna.TodoList.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static java.lang.Boolean.FALSE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "memo")
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "~ Plan title cannot be none ~")
    private String message;

    private String tag = "Others";

    private Boolean complete = FALSE;

    private Boolean publicity = FALSE;

    private LocalDate localDate_created = LocalDate.now();

    private LocalDate localDate_modified = LocalDate.now();

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)//可选属性optional=false,表示user不能为空。
    @JoinColumn(name = "memo_users_id")//设置在memo表中的关联字段(外键)
    private Users users;//memo所属user
}
