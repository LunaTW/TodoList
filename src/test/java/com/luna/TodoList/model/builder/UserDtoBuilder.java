package com.luna.TodoList.model.builder;

import com.luna.TodoList.model.User;

import java.time.LocalDate;

//@Builder  todo: refactor
public class UserDtoBuilder {
    private Long userId = (long)1;
    private String username = "Luna";
    private LocalDate DateOfBirth = LocalDate.of(1995,11,18);
    private String email = "unswlun@gmail.com";
    private String phone = "0412218970";

    public UserDtoBuilder withUsername(String username){
        this.username = username;
        return this;
    }
    public UserDtoBuilder withDateOfBirth(LocalDate DateOfBirth){
        this.DateOfBirth = DateOfBirth;
        return this;
    }
    public UserDtoBuilder withEmail(String email){
        this.email = email;
        return this;
    }
    public UserDtoBuilder withPhone(String phone){
        this.phone = phone;
        return this;
    }

    public User build(){
        return new User(userId,username,DateOfBirth,email,phone);
    }

}

