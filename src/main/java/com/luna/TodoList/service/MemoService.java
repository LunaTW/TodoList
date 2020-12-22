package com.luna.TodoList.service;

import com.luna.TodoList.dto.MemoRequestDto;
import com.luna.TodoList.model.Memo;
import com.luna.TodoList.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoService {
    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public Memo addMemo(MemoRequestDto memoRequestDto){
        return memoRepository.addMemo(memoRequestDto);
    }

    public Memo getMemoById(Long id){
        return memoRepository.getMemoById(id);
    }

    public List<Memo> getAllMemos(){
        return memoRepository.getAllMemos();
    }
}
