package com.luna.TodoList.controller;

import com.luna.TodoList.service.MemoService;
import com.luna.TodoList.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserService userService;
    private final MemoService memoService;

    public AdminController(UserService userService, MemoService memoService) {
        this.userService = userService;
        this.memoService = memoService;
    }
}
