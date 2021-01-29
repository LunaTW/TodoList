package com.luna.TodoList.service;

import com.luna.TodoList.dto.UserRequestDto;
import com.luna.TodoList.model.User;
import com.luna.TodoList.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(UserRequestDto userRequestDto) throws Exception {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();
        User user = userRepository.findByUsername(username);
        if (user.getPassword().equals(password)){
            throw new Exception("Wrong Username/Password");
        }else {
            return user;
        }
    }
}
