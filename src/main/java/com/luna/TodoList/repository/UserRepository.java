package com.luna.TodoList.repository;

import com.luna.TodoList.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);

    @Query("select u.admin from Users u where u.userId = :userId")
    Boolean findAdminByUserId(Long userId);

    @Query("select u.userId from Users u where u.username = :username")
    long findUserIdByUsername(String username);

}
