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

    // -- For Admin Feature --
    // Todo: getMemoByID(check message belong to this user,will get userId as well)

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Memo addMemo(@RequestBody @Valid MemoRequestDto memoRequestDto) {
        return memoService.addMemo(memoRequestDto);
    }

    @PostMapping("/share")
    @ResponseStatus(HttpStatus.CREATED)
    public Memo shareMemo(@RequestParam Long userId, @RequestBody Memo memo) {
        return memoService.shareMemo(userId, memo);
    }

    @GetMapping("/{id}")
    public Memo getMemoById(@PathVariable Long id){
        return memoService.getMemoById(id);
    }

    @GetMapping
    public List<Memo> getAllMemos(){
        return memoService.getAllMemos();
    }

    @GetMapping("/tags/{tag}")
    public List<Memo> getMemosByTag(@PathVariable String tag){
        return memoService.getMemosByTag(tag);
    }

    @GetMapping("/keywords/{keyword}")
    public List<Memo> getMemosByKeyword(@PathVariable String keyword){
        return memoService.getMemosByKeyword(keyword);
    }

    @GetMapping("/complete")
    public List<Memo> getMemosByCompleted(@RequestParam Boolean completed){
        return memoService.getMemosByCompleted(completed);
    }

    @GetMapping("/users/{userId}")
    public List<Memo> getMemoByUserId(@PathVariable Long userId){
        return memoService.getMemoByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteMemosById(@PathVariable Long id){
        memoService.deleteMemosById(id);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteMemosByUserId(@PathVariable Long userId){
        memoService.deleteMemosByUserId(userId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Memo updateMemo(@PathVariable Long id, @RequestBody @Valid MemoRequestDto memoRequestDto){
        return memoService.updateMemo(id,memoRequestDto);
    }
}
