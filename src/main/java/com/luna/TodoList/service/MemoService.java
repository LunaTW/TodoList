package com.luna.TodoList.service;

import com.luna.TodoList.dto.MemoRequestDto;
import com.luna.TodoList.exception.MemoNotFoundException;
import com.luna.TodoList.model.Memo;
import com.luna.TodoList.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemoService {
    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public Memo addMemo(Memo memo) throws MemoNotFoundException {
        memoRepository.save(memo);
        return memo;
    }

    public Memo getMemoById(Long id) throws MemoNotFoundException {
        Memo memo = memoRepository.findById(id).orElseThrow(() -> new MemoNotFoundException("Memo not exist"));
        return memo;
    }

    public List<Memo> getMemosByTag(String tag){
        List<Memo> allMemosByTag = memoRepository.findAll()
                .stream()
                .filter(memo -> memo.getTag().equals(tag))
                .collect(Collectors.toList());
        return allMemosByTag;
    }

    public List<Memo> getMemosByCompleted(Boolean completed){
        List<Memo> allMemosByTag = memoRepository.findAll()
                .stream()
                .filter(memo -> memo.getComplete().equals(completed))
                .collect(Collectors.toList());
        return allMemosByTag;
    }

    public List<Memo> getAllMemos() {
        return memoRepository.findAll();
    }

    public void deleteMemosById(Long id) throws MemoNotFoundException {
        memoRepository.findById(id).orElseThrow(() -> new MemoNotFoundException("Memo not exist"));
        memoRepository.deleteById(id);
    }

    public Memo updateMemo(Long id, MemoRequestDto memoRequestDto) throws MemoNotFoundException {
        memoRepository.findById(id).orElseThrow(() -> new MemoNotFoundException("Memo not exist"));
        Memo memoToUpdate = memoRepository.getOne(id);
        memoToUpdate.setMessage(memoRequestDto.getMessage());
        memoToUpdate.setTag(memoRequestDto.getTag());
        memoToUpdate.setComplete(memoRequestDto.getComplete());
        memoToUpdate.setLocalDate_modified(LocalDate.now());
        memoRepository.save(memoToUpdate);
        return memoToUpdate;
    }
}
