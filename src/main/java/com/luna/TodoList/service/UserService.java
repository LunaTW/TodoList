package com.luna.TodoList.service;

import com.luna.TodoList.dto.UserRequestDto;
import com.luna.TodoList.exception.NoAccessException;
import com.luna.TodoList.exception.NotFoundException;
import com.luna.TodoList.model.User;
import com.luna.TodoList.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void deleteUserById(Long id, Long loginId) throws NoAccessException {

        if (id.equals(loginId) || userRepository.findAdminByUserId(loginId)) {
            userRepository.findById(id);
            userRepository.deleteById(id);
        } else {
            throw new NoAccessException("You do not have access");
        }
    }

    public User updateUser(Long id, Long loginId, UserRequestDto userRequestDto) throws NotFoundException {

        long userToUpdateId = userRepository.findUserIdByUsername(userRequestDto.getUsername());

        if (userToUpdateId != id ){
            throw new NoAccessException("You can only update your profile");
        } else if (userRepository.findAdminByUserId(loginId) || loginId.equals(userToUpdateId)){
            userRepository.findById(id);
            User userToUpdate = userRepository.findById(id);
            userToUpdate.setUsername(userRequestDto.getUsername());
            userToUpdate.setDateOfBirth(userRequestDto.getDateOfBirth());
            userToUpdate.setEmail(userRequestDto.getEmail());
            userToUpdate.setPhone(userRequestDto.getPhone());
            userRepository.save(userToUpdate);
            return userToUpdate;
        } else {
            throw new NoAccessException("You do not have access");
        }
    }

    public List<User> getAllUsers(Long loginId) throws NoAccessException {

        if (userRepository.findAdminByUserId(loginId)) {
            return userRepository.findAll();
        } else {
            throw new NoAccessException("You do not have access");
        }
    }

    public User getUserById(Long id, Long loginId) throws NotFoundException {

        User userInfo = userRepository.findById(id);

        if (id.equals(loginId) || userRepository.findAdminByUserId(loginId)) {
            return userInfo;
        } else {
            return User.builder().username(userInfo.getUsername()).build();
        }
    }
}
