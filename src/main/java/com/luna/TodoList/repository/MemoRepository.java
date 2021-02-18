package com.luna.TodoList.repository;

import com.luna.TodoList.model.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {

    Long deleteByUserId(Long userId);

    List<Memo> findByUserId(Long userId);

    @Query("select m.userId from Memo m where m.id = :id")
    long findUserIdById(Long id);
}