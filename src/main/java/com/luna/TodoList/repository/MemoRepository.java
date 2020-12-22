package com.luna.TodoList.repository;

import com.luna.TodoList.dto.MemoRequestDto;
import com.luna.TodoList.model.Memo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.FALSE;

@Repository
public class MemoRepository {
    private Map<Long, Memo> memoMap = new HashMap<>();

    public MemoRepository() {
        this.memoMap = memoMap;
    }

    public Memo getMemoById(Long id){
        return memoMap.get(id);
    }

    public Memo addMemo(MemoRequestDto memoRequestDto){
        long id = memoMap.size() + 1;
        Memo memo = new Memo(id,memoRequestDto.getMessage(),memoRequestDto.getTag(),FALSE, LocalDate.now());  // To ask (up)
        memoMap.put(id,memo);
        return memo;
    }

    public List<Memo> getAllMemos(){
        return new ArrayList<>(memoMap.values());
    }
}
