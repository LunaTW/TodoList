package com.luna.TodoList.service;

import com.luna.TodoList.dto.UserRequestDto;
import com.luna.TodoList.exception.NotFoundException;
import com.luna.TodoList.model.Memo;
import com.luna.TodoList.model.User;
import com.luna.TodoList.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(UserRequestDto userRequestDto){
        User user = User.builder()
                .username(userRequestDto.getUsername())
                .DateOfBirth(userRequestDto.getDateOfBirth())
                .email(userRequestDto.getEmail())
                .phone(userRequestDto.getPhone())
                .build();
        userRepository.save(user);
        return user;
    }

    public void deleteUser(Long id){
        userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not exist"));
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, UserRequestDto userRequestDto){
        userRepository.findById(id).orElseThrow(()-> new NotFoundException("User is not exist!"));
        User userToUpdate = userRepository.getOne(id);
        userToUpdate.setUsername(userRequestDto.getUsername());
        userToUpdate.setDateOfBirth(userRequestDto.getDateOfBirth());
        userToUpdate.setEmail(userRequestDto.getEmail());
        userToUpdate.setPhone(userRequestDto.getPhone());
        userRepository.save(userToUpdate);
        return userToUpdate;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException("User not exit"));
    }

}
