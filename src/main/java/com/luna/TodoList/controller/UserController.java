package com.luna.TodoList.controller;

import com.luna.TodoList.dto.UserRequestDto;
import com.luna.TodoList.model.User;
import com.luna.TodoList.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id, @RequestParam long loginId) throws Exception {
        userService.deleteUserById(id, loginId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User updateUser(@PathVariable Long id, @RequestParam Long loginId, @RequestBody @Valid UserRequestDto userRequestDto) throws Exception {
        return userService.updateUser(id, loginId, userRequestDto);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id, @RequestParam Long loginId)  {
        return userService.getUserById(id, loginId);
    }

    @GetMapping
    public List<User> getAllUsers(@RequestParam Long loginId) {
        return userService.getAllUsers(loginId);
    }
}
