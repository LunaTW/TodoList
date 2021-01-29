package com.luna.TodoList.controller;

import com.luna.TodoList.dto.UserRequestDto;
import com.luna.TodoList.model.User;
import com.luna.TodoList.service.AuthService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/login")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public User login(@RequestBody @Valid UserRequestDto userRequestDto) throws Exception {
        return authService.login(userRequestDto);
    }
}
