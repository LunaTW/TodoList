package com.luna.TodoList.service;

import com.luna.TodoList.dto.MemoRequestDto;
import com.luna.TodoList.model.Memo;
import com.luna.TodoList.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MemoService {
    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public Memo addMemo(Memo memo){
        memoRepository.save(memo);
        return memo;
    }

    public Optional<Memo> getMemoById(Long id){
        return memoRepository.findById(id);
    }

    public List<Memo> getAllMemos(){
        return memoRepository.findAll();
    }

    public void deleteMemosById(Long id){
        memoRepository.deleteById(id);
    }

    public Memo updateMemo(Long id, MemoRequestDto memoRequestDto){
        Memo memoToUpdate = memoRepository.findById(id).orElse(null);
        memoToUpdate.setMessage(memoRequestDto.getMessage());
        memoToUpdate.setTag(memoRequestDto.getMessage());
        memoToUpdate.setComplete(memoRequestDto.getComplete());
        memoToUpdate.setLocalDate_modified(LocalDate.now());
        return memoToUpdate;
    }
}
