package com.luna.TodoList.controller;

import com.luna.TodoList.dto.MemoRequestDto;
import com.luna.TodoList.model.Memo;
import com.luna.TodoList.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
        Memo memo = Memo.builder().message(memoRequestDto.getMessage())
                                    .tag(memoRequestDto.getTag())
                                    .build();
        return memoService.addMemo(memo);
    }

    @GetMapping("/{id}")
    public Optional<Memo> getMemoById(@PathVariable Long id){
        return memoService.getMemoById(id);
    }

    @GetMapping
    public List<Memo> getAllMemos(){
        return memoService.getAllMemos();
    }

    @DeleteMapping("/{id}")
    public void deleteMemosById(@PathVariable Long id){
        memoService.deleteMemosById(id);
    }
    // Todo: delete by tag or complete

    @PutMapping("/{id}")
    public Memo updateMemo(@PathVariable Long id, @RequestBody @Valid MemoRequestDto memoRequestDto){
        return memoService.updateMemo(id,memoRequestDto);
    }
}
