package com.luna.TodoList.controller;

import com.luna.TodoList.dto.MemoRequestDto;
import com.luna.TodoList.model.Memo;
import com.luna.TodoList.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/memos")
public class MemoController {
    private MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Memo addMemo(@RequestBody @Valid MemoRequestDto memoRequestDto){
        return memoService.addMemo(memoRequestDto);
    }

    @GetMapping("/{id}")
    public Memo getMemoById(@PathVariable Long id){
        return memoService.getMemoById(id);
    }

    @GetMapping
    public List<Memo> getAllMemos(){
        return memoService.getAllMemos();
    }
}
