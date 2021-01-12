package com.luna.TodoList.repository;

import com.luna.TodoList.model.Memo;
import com.luna.TodoList.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
