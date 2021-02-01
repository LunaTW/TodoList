package com.luna.TodoList.controller;

import com.luna.TodoList.dto.UserPublicDto;
import com.luna.TodoList.dto.UserRequestDto;
import com.luna.TodoList.model.User;
import com.luna.TodoList.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id, @RequestBody @Valid UserRequestDto userRequestDto) throws Exception {
        userService.deleteUserById(id, userRequestDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User updateUser(@PathVariable Long id, @RequestBody @Valid UserRequestDto userRequestDto) throws Exception {
        return userService.updateUser(id, userRequestDto);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id, @RequestBody @Valid UserRequestDto userRequestDto) throws Exception {
        return userService.getUserById(id, userRequestDto);
    }

    @GetMapping("/{id}/{usersInfo}")
    public UserPublicDto getOtherUserById(@PathVariable Long id, @PathVariable Long usersInfo) throws Exception {
        return userService.getOtherUserById(id, usersInfo);
    }
}
