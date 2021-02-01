package com.luna.TodoList.service;

import com.luna.TodoList.dto.UserPublicDto;
import com.luna.TodoList.dto.UserRequestDto;
import com.luna.TodoList.exception.NotFoundException;
import com.luna.TodoList.model.User;
import com.luna.TodoList.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String deleteUserById(Long id, UserRequestDto userRequestDto) throws Exception {

        Long userLogin = userRepository.findByUsername(userRequestDto.getUsername()).getUserId();

        if (id.equals(userLogin) || userRequestDto.getAdmin()) {
            userRepository.findById(id);
            userRepository.deleteById(id);
            return "SUCCESS";
        } else {
            throw new Exception("You do not have access");
        }
    }

    public User updateUser(Long id, UserRequestDto userRequestDto) throws Exception {

        Long userLogin = userRepository.findByUsername(userRequestDto.getUsername()).getUserId();

        if (id.equals(userLogin) || userRequestDto.getAdmin()) {
            userRepository.findById(id);
            User userToUpdate = userRepository.findById(id);
            userToUpdate.setUsername(userRequestDto.getUsername());
            userToUpdate.setDateOfBirth(userRequestDto.getDateOfBirth());
            userToUpdate.setEmail(userRequestDto.getEmail());
            userToUpdate.setPhone(userRequestDto.getPhone());
            userRepository.save(userToUpdate);
            return userToUpdate;
        } else {
            throw new Exception("You do not have access");
        }
    }

    public List<User> getAllUsers(Long loginId) throws Exception {
        if (userRepository.findAdminByUserId(loginId)) {
            return userRepository.findAll();
        } else {
            throw new Exception("You do not have access");

        }
    }

    public User getUserById(Long id, UserRequestDto userRequestDto) throws Exception {

        Long userLogin = userRepository.findByUsername(userRequestDto.getUsername()).getUserId();

        if (id.equals(userLogin) || userRequestDto.getAdmin()) {
            return userRepository.findById(id);
        } else {
            throw new Exception("You do not have access");
        }
    }

    public UserPublicDto getOtherUserById(Long id, Long usersInfoId) throws Exception {
        return UserPublicDto.builder().username(userRepository.findUsernameById(usersInfoId)).build();
    }
}
