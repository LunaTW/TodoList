package com.luna.TodoList.repository;

import com.luna.TodoList.dto.MemoRequestDto;
import com.luna.TodoList.model.Memo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoRepository {
    private Map<Long, Memo> memoMap = new HashMap<>();
    public static Long indexOfMemo = (long) 0;

    public MemoRepository() {
        this.memoMap = memoMap;
    }

    public Memo addMemo(MemoRequestDto memoRequestDto) {
        indexOfMemo += 1;
        Memo memo = new Memo(indexOfMemo, memoRequestDto.getMessage(), memoRequestDto.getTag(), memoRequestDto.getComplete(), LocalDate.now(), LocalDate.now());  // To ask (up)
        memoMap.put(indexOfMemo, memo);
        return memo;
    }

    public Memo getMemoById(Long id) {
        return memoMap.get(id);
    }

    public List<Memo> getAllMemos() {
        return new ArrayList<>(memoMap.values());
    }

    public void deleteMemosById(Long id) {
        memoMap.remove(id);
    }

    public Memo updateMemo(Long id, MemoRequestDto memoRequestDto) {
        Memo memoToUpdate = memoMap.get(id);
        memoToUpdate.setComplete(memoRequestDto.getComplete());
        memoToUpdate.setLocalDate_modified(LocalDate.now());
        memoToUpdate.setMessage(memoRequestDto.getMessage());
        memoToUpdate.setTag(memoRequestDto.getTag());
        return memoToUpdate;
    }
}
