package com.luna.TodoList.controller;

import com.luna.TodoList.dto.UserRequestDto;
import com.luna.TodoList.exception.NotFoundException;
import com.luna.TodoList.model.User;
import com.luna.TodoList.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(UserController.class)
@AutoConfigureJsonTesters
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private JacksonTester<User> userJson;

    @Autowired
    private MockMvc mockMvc;

    private User user;
    private User userToUpdate;
    private UserRequestDto userRequestDto;
    private UserRequestDto userRequestDtoToUpdate;
    private List<User> userList;
    private List<User> userEmptyList;

    @Nested
    class CreateUser {

        @BeforeEach
        public void setUp() {
            Long userId = (long)1;
            String username = "Luna";
            LocalDate DateOfBirth = LocalDate.of(1995,11,18);
            String email = "unswlun@gmail.com";
            String phone = "0412218970";

            userRequestDto = UserRequestDto.builder()
                    .username(username)
                    .DateOfBirth(DateOfBirth)
                    .email(email)
                    .phone(phone)
                    .build();

            user = User.builder()
                    .userId(userId)
                    .username(username)
                    .DateOfBirth(DateOfBirth)
                    .email(email)
                    .phone(phone)
                    .build();
        }

        @AfterEach
        public void afterEach() {
            Mockito.reset(userService);
        }

        @Nested
        class WhenRequestIsValid {

            @Test
            public void should_create_new_user_and_return_json() throws Exception{
                when(userService.addUser(userRequestDto)).thenReturn(user);

                mockMvc.perform(post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson.write(user).getJson()))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.username",containsString("Luna")));

            verify(userService).addUser(userRequestDto);
            }
        }

        @Nested
        class WhenRequestIsNotValid {

            @Test
            public void should_not_create_new_user_and_return_400() throws Exception{

                mockMvc.perform(post("/users"))
                        .andExpect(status().isBadRequest());

                verify(userService,times(0)).addUser(userRequestDto);
            }
        }

    }


    @Nested
    class DeleteUser{

        @Nested
        class whenUserExists {

            @Test
            public void should_delete_user_by_id() throws Exception{
                when(userService.deleteUserById(1L)).thenReturn("SUCCESS");

                mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}" ,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

                verify(userService,times(1)).deleteUserById(1L);

            }
        }

        @Nested
        class whenUserNotExists {

            @Test
            public void should_delete_user_by_id() throws Exception{
                when(userService.deleteUserById(1L)).thenThrow(new NotFoundException("User Not Found"));

                mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}" ,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                        .andExpect(jsonPath("$.message",containsString("User Not Found")));

                verify(userService,times(1)).deleteUserById(1L);

            }

        }

    }


    @Nested
    class GetUser{

        @BeforeEach
        public void setUp() {
            Long userId = (long)1;
            String username = "Luna";
            LocalDate DateOfBirth = LocalDate.of(1995,11,18);
            String email = "unswlun@gmail.com";
            String phone = "0412218970";

            userRequestDto = UserRequestDto.builder()
                    .username(username)
                    .DateOfBirth(DateOfBirth)
                    .email(email)
                    .phone(phone)
                    .build();

            user = User.builder()
                    .userId(userId)
                    .username(username)
                    .DateOfBirth(DateOfBirth)
                    .email(email)
                    .phone(phone)
                    .build();

            userList = new ArrayList<>();
            userEmptyList = new ArrayList<>();
            userList.add(user);

        }

        @AfterEach
        public void afterEach() {
            Mockito.reset(userService);
        }

        @Nested
        class whenUserIdExists {

            @Test
            public void should_return_user_by_id() throws Exception{
                when(userService.getUserById(1L)).thenReturn(user);

                mockMvc.perform(get("/users/{id}",1L))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.username",is("Luna")));

                verify(userService,times(1)).getUserById(1L);
            }

            @Test
            public void should_return_all_users_if_there_have() throws Exception {
                when(userService.getAllUsers()).thenReturn(userList);

                mockMvc.perform(get("/users",1L))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$",hasSize(1)));

                verify(userService,times(1)).getAllUsers();
            }

            @Test
            public void should_return_empty_list_if_there_have_no_user() throws Exception {
                when(userService.getAllUsers()).thenReturn(userEmptyList);

                mockMvc.perform(get("/users",1L))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$",hasSize(0)));

                verify(userService,times(1)).getAllUsers();
            }
        }

        @Nested
        class whenUserIdNotExists {

            @Test
            public void should_return_NOT_FOUND() throws Exception{
                when(userService.getUserById(1L)).thenThrow(new NotFoundException("USER NOT EXIST"));

                mockMvc.perform(get("/users/{id}",1L))
                        .andExpect(status().isNotFound())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.message",containsString("USER NOT EXIST")));

                verify(userService,times(1)).getUserById(1L);
            }

        }

    }

    @Nested
    class PutUser{
        @BeforeEach
        public void setUp() {
            Long userId = (long)1;
            String username = "Luna";
            LocalDate DateOfBirth = LocalDate.of(1995,11,18);
            String email = "unswlun@gmail.com";
            String phone = "0412218970";

            userRequestDto = UserRequestDto.builder()
                    .username(username)
                    .DateOfBirth(DateOfBirth)
                    .email(email)
                    .phone(phone)
                    .build();

            userRequestDtoToUpdate = UserRequestDto.builder()
                    .username("NotLuna")
                    .DateOfBirth(DateOfBirth)
                    .email(email)
                    .phone(phone)
                    .build();

            user = User.builder()
                    .userId(userId)
                    .username(username)
                    .DateOfBirth(DateOfBirth)
                    .email(email)
                    .phone(phone)
                    .build();

            userToUpdate = User.builder()
                    .userId(userId)
                    .username("NotLuna")
                    .DateOfBirth(DateOfBirth)
                    .email(email)
                    .phone(phone)
                    .build();
        }

        @AfterEach
        public void afterEach() {
            Mockito.reset(userService);
        }

        @Nested
        class whenUserExist {

            @Test
            public void should_update_user_information_by_userId() throws Exception {
                when(userService.updateUser(1L,userRequestDtoToUpdate)).thenReturn(userToUpdate);

                mockMvc.perform(put("/users/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson.write(userToUpdate).getJson())
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.username",containsString("NotLuna")))
                        .andExpect(status().isAccepted());

                verify(userService,times(1)).updateUser(1L,userRequestDtoToUpdate);

            }
        }

        @Nested
        class whenUserNotExist {

            @Test
            public void should_return_400() throws Exception {
                when(userService.updateUser(1L,userRequestDtoToUpdate)).thenThrow(new NotFoundException("NOT EXIST USER"));

                mockMvc.perform(put("/users/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson.write(userToUpdate).getJson())
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound());

                verify(userService,times(1)).updateUser(1L,userRequestDtoToUpdate);

            }
        }
    }
}
