package com.luna.TodoList.controller;


import com.luna.TodoList.dto.UserRequestDto;
import com.luna.TodoList.model.Memo;
import com.luna.TodoList.model.User;
import com.luna.TodoList.service.MemoService;
import com.luna.TodoList.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserService userService;
    private final MemoService memoService;

    public AdminController(UserService userService, MemoService memoService) {
        this.userService = userService;
        this.memoService = memoService;
    }

    @GetMapping(value = "/users")
    public List<User> getAllUsers(@RequestParam Long loginId) throws Exception {
        return userService.getAllUsers(loginId);
    }

    @GetMapping(value = "/memos")
    public List<Memo> getAllMemos(@RequestParam Long loginId) throws Exception {
        return memoService.getAllMemos(loginId);
    }
}
