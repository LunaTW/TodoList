package com.luna.TodoList.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    private String username;

    @NotNull
    private String password;

    private LocalDate DateOfBirth;

    private String email;

    private String phone;

    private Boolean admin=FALSE;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    //级联保存、更新、删除、刷新;延迟加载。当删除用户，会级联删除该用户的所有文章
    //拥有mappedBy注解的实体类为关系被维护端
    //mappedBy="userId"中的userId是Article中的userId属性
    private List<Memo> memoList;//文章列表
}
