package com.luna.TodoList.repository;

import com.luna.TodoList.exception.NotFoundException;
import com.luna.TodoList.model.Memo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MemoRepository {
    public final List<Memo> memos = new ArrayList<>();
    public static Long memoTotal = 0L;

    public void save(Memo memo) {
        memoTotal += 1;
        memo.setId(memoTotal);
        memos.add(memo);
    }

    public Long deleteByUserId(Long userId) {
        List<Memo> memoToDelete = memos.stream().filter(memo -> memo.getUserId().equals(userId)).collect(Collectors.toList());
        long totalNumber = 0L;
        for (Memo x : memoToDelete) {
            memos.remove(x);
            totalNumber += 1;
        }
        return totalNumber;
    }

    public void deleteById(Long memoId) {
        Memo memoToRemove = memos.stream().filter(memo -> memo.getId().equals(memoId)).findFirst().orElseThrow(() -> new NotFoundException("Memo not exist"));
        memos.remove(memoToRemove);
    }

    public Long findUserIdById(Long id) {
        return memos.stream().filter(memo -> memo.getId().equals(id)).findFirst().orElseThrow(() -> new NotFoundException("Memo not exist")).getUserId();
    }

    public Memo findById(Long id) {
        return memos.stream().filter(memo -> memo.getId().equals(id)).findFirst().orElseThrow(() -> new NotFoundException("Memo not exist"));
    }

    public List<Memo> findAll() {
        return memos;
    }

}