package com.luna.TodoList.service;

import com.luna.TodoList.dto.MemoRequestDto;
import com.luna.TodoList.exception.NotFoundException;
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

    public Memo addMemo(MemoRequestDto memoRequestDto) throws NotFoundException {
        Memo memo = Memo.builder().message(memoRequestDto.getMessage())
                .tag(memoRequestDto.getTag())
                .complete(memoRequestDto.getComplete())
                .publicity(memoRequestDto.getPublicity())
                .localDate_created(LocalDate.now())
                .localDate_modified(LocalDate.now())
                .userId(memoRequestDto.getUserId())
                .build();
        memoRepository.save(memo);
        return memo;
    }

    public Memo getMemoById(Long id) throws NotFoundException {
        return memoRepository.findById(id).orElseThrow(() -> new NotFoundException("Memo not exist"));
    }

    public List<Memo> getMemosByTag(String tag){
        List<Memo> allMemosByTag = memoRepository.findAll()
                .stream()
                .filter(memo -> memo.getTag().equals(tag))
                .collect(Collectors.toList());
        return allMemosByTag;
    }

    public List<Memo> getMemosByCompleted(Boolean completed){
        List<Memo> allMemosByCompleted = memoRepository.findAll()
                .stream()
                .filter(memo -> memo.getComplete().equals(completed))
                .collect(Collectors.toList());
        return allMemosByCompleted;
    }

    public List<Memo> getMemoByKeyword(String keyword){
        List<Memo> allMemosByKeyword = memoRepository.findAll()
                .stream()
                .filter(memo -> memo.getMessage().contains(keyword))
                .collect(Collectors.toList());
        return allMemosByKeyword;
    }

    public List<Memo> getAllMemos() {
        return memoRepository.findAll();
    }

    public List<Memo> getMemoByUserId(Long id){
        List<Memo> allMemosByUserId = memoRepository.findAll()
                .stream()
                .filter(memo -> memo.getUserId().equals(id))
                .collect(Collectors.toList());
        return allMemosByUserId;
    }

    public void deleteMemosById(Long id) throws NotFoundException {
        memoRepository.findById(id).orElseThrow(() -> new NotFoundException("Memo not exist"));
        memoRepository.deleteById(id);
    }

    // cannot change user for now: for design
    public Memo updateMemo(Long id, MemoRequestDto memoRequestDto) throws NotFoundException {
        memoRepository.findById(id).orElseThrow(() -> new NotFoundException("Memo not exist"));
        Memo memoToUpdate = memoRepository.getOne(id);
        memoToUpdate.setMessage(memoRequestDto.getMessage());
        memoToUpdate.setTag(memoRequestDto.getTag());
        memoToUpdate.setComplete(memoRequestDto.getComplete());
        memoToUpdate.setLocalDate_modified(LocalDate.now());
        memoToUpdate.setPublicity(memoRequestDto.getPublicity());
        memoRepository.save(memoToUpdate);
        return memoToUpdate;
    }
}
