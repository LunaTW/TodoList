package com.luna.TodoList.service;

import com.luna.TodoList.dto.UserRequestDto;
import com.luna.TodoList.exception.NoAccessException;
import com.luna.TodoList.exception.NotFoundException;
import com.luna.TodoList.model.Users;
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
            userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not exist"));
            userRepository.deleteById(id);
        } else {
            throw new NoAccessException("You do not have access");
        }
    }

    public Users updateUser(Long id, Long loginId, UserRequestDto userRequestDto) throws NotFoundException {

        long userToUpdateId = userRepository.findUserIdByUsername(userRequestDto.getUsername());

        if (userToUpdateId != id ){
            throw new NoAccessException("You can only update your profile");
        } else if (userRepository.findAdminByUserId(loginId) || loginId.equals(userToUpdateId)){
            userRepository.findById(id).orElseThrow(()-> new NotFoundException("User is not exist!"));
            Users usersToUpdate = userRepository.getOne(id);
            usersToUpdate.setUsername(userRequestDto.getUsername());
            usersToUpdate.setDateOfBirth(userRequestDto.getDateOfBirth());
            usersToUpdate.setEmail(userRequestDto.getEmail());
            usersToUpdate.setPhone(userRequestDto.getPhone());
            userRepository.save(usersToUpdate);
            return usersToUpdate;
        } else {
            throw new NoAccessException("You do not have access");
        }
    }

    public List<Users> getAllUsers(Long loginId) throws NoAccessException {

        if (userRepository.findAdminByUserId(loginId)) {
            return userRepository.findAll();
        } else {
            throw new NoAccessException("You do not have access");
        }
    }

    public Users getUserById(Long id, Long loginId) throws NotFoundException {

        Users usersInfo = userRepository.findById(id).orElseThrow(()-> new NotFoundException("User not exit"));

        if (id.equals(loginId) || userRepository.findAdminByUserId(loginId)) {
            return usersInfo;
        } else {
            return Users.builder().username(usersInfo.getUsername()).build();
        }
    }
}
