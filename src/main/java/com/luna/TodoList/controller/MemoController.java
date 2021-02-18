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
    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Memo addMemo(@RequestParam Long loginUserId, @RequestBody @Valid MemoRequestDto memoRequestDto) {
        return memoService.addMemo(loginUserId, memoRequestDto);
    }

    @DeleteMapping("/{memoId}")
    public String deleteMemosById(@PathVariable Long memoId, @RequestParam Long loginUserId) {
        return memoService.deleteMemosById(memoId, loginUserId);
    }

    @DeleteMapping("/users/{userId}")
    public Long deleteMemosByUserId(@PathVariable Long userId, @RequestParam Long loginUserId) {
        return memoService.deleteMemosByUserId(userId, loginUserId);
    }

    @PutMapping("/{memoId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Memo updateMemo(@PathVariable Long memoId, @RequestParam Long loginUserId, @RequestBody @Valid MemoRequestDto memoRequestDto) {
        return memoService.updateMemo(memoId, loginUserId, memoRequestDto);
    }

    @GetMapping("/{memoId}")
    public Memo getMemoById(@PathVariable Long memoId, @RequestParam Long loginUserId) {
        return memoService.getMemoById(memoId, loginUserId);
    }

    @GetMapping("/users/{userId}")
    public List<Memo> getMemoByUserId(@PathVariable Long userId, @RequestParam Long loginUserId) {
        return memoService.getMemoByUserId(userId, loginUserId);
    }

    @GetMapping("/tags/{tag}")
    public List<Memo> getMemosByTag(@PathVariable String tag, @RequestParam Long loginUserId) {
        return memoService.getMemosByTag(tag, loginUserId);
    }

    @GetMapping("/keywords/{keyword}")
    public List<Memo> getMemosByKeyword(@PathVariable String keyword, @RequestParam Long loginUserId) {
        return memoService.getMemosByKeyword(keyword, loginUserId);
    }

    @GetMapping("/complete")
    public List<Memo> getMemosByCompleted(@RequestParam Boolean completed, @RequestParam Long loginUserId) {
        return memoService.getMemosByCompleted(completed, loginUserId);
    }

    @GetMapping
    public List<Memo> getAllMemos(@RequestParam Long loginId) {
        return memoService.getAllMemos(loginId);
    }
}
