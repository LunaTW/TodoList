package com.luna.TodoList.controller;

import com.luna.TodoList.dto.AuthRequestDto;
import com.luna.TodoList.model.User;
import com.luna.TodoList.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody @Valid AuthRequestDto authRequestDto) {
        return authService.register(authRequestDto);
    }


    @GetMapping(value = "/login")
    public User login(@RequestBody @Valid AuthRequestDto authRequestDto) {
        return authService.login(authRequestDto);
    }
}
