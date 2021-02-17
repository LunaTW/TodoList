package com.luna.TodoList.repository;

import com.luna.TodoList.exception.NotFoundException;
import com.luna.TodoList.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Repository
public class UserRepository {

    public final List<User> users = new ArrayList<>();

    public void save(User user) {
        long userTotal = (long) users.size();
        user.setUserId(userTotal+1);
        users.add(user);
    }

    public User findByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    public User findById(Long id) throws NotFoundException {
        return users.stream().filter(user -> user.getUserId().equals(id)).findFirst().orElseThrow(() -> new NotFoundException("User not exist") );
    }

    public List<User> findAll() {
        return users;
    }

    public void deleteById(Long id) throws NotFoundException {
        User userToDelete = users.stream().filter(user -> user.getUserId().equals(id)).findFirst().orElseThrow(() -> new NotFoundException("User not exist") );
        users.remove(userToDelete);
    }

    public Boolean findAdminByUserId(Long id) {
        if (users.stream().filter(user -> user.getUserId().equals(id) && user.getAdmin().equals(TRUE)).findFirst().orElse(null) == null) {
            return FALSE;
        } else {
            return TRUE;
        }
    }

    public Long findUserIdByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElseThrow(() -> new NotFoundException("User not exist")).getUserId();
    }

}



