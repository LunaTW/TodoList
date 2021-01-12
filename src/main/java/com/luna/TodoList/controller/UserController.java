package com.luna.TodoList.controller;

import com.luna.TodoList.dto.UserRequestDto;
import com.luna.TodoList.model.User;
import com.luna.TodoList.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController(value = "/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody @Valid UserRequestDto userRequestDto){
        return userService.addUser(userRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User updateUser(@PathVariable Long id,@RequestBody @Valid UserRequestDto userRequestDto){
        return userService.updateUser(id,userRequestDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
}
