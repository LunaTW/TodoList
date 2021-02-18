package com.luna.TodoList.service;

import com.luna.TodoList.dto.AuthRequestDto;
import com.luna.TodoList.exception.IncorrectInformationException;
import com.luna.TodoList.exception.UserAlreadyExistException;
import com.luna.TodoList.model.User;
import com.luna.TodoList.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String register(AuthRequestDto authRequestDto) throws UserAlreadyExistException {
        String username = authRequestDto.getUsername();
        if (userRepository.findByUsername(username) != null) {
            throw new UserAlreadyExistException("User Already Exist! Please Try Another One");
        } else {
            User user = User.builder()
                    .username(authRequestDto.getUsername())
                    .password(authRequestDto.getPassword())
                    .admin(authRequestDto.getAdmin())
                    .build();
            userRepository.save(user);
            return "SUCCESS";
        }
    }

    public User login(AuthRequestDto authRequestDto) throws IncorrectInformationException {

        String username = authRequestDto.getUsername();
        String password = authRequestDto.getPassword();
        if (userRepository.findByUsername(username) == null) {
            throw new IncorrectInformationException("User not exist");
        }

        String passwordToCompare = userRepository.findByUsername(username).getPassword();

        if (passwordToCompare.equals(password)) {
            return userRepository.findByUsername(username);
        } else {
            throw new IncorrectInformationException("Wrong Username/Password");
        }
    }
}
