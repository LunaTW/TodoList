//package com.luna.TodoList.controller;
//
//import com.luna.TodoList.dto.AuthRequestDto;
//import com.luna.TodoList.exception.UserAlreadyExistException;
//import com.luna.TodoList.model.User;
//import com.luna.TodoList.service.AuthService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.json.JacksonTester;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(AuthController.class)
//@AutoConfigureJsonTesters
//public class AuthControllerTest {
//
//    @MockBean
//    private AuthService authService;
//
//    @Autowired
//    private JacksonTester<User> userJson;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private User user;
//    private AuthRequestDto authRequestDto;
//
//    @BeforeEach
//    public void setUp() {
//
//        authRequestDto = authRequestDto.builder()
//                .username("Luna")
//                .password("hahaha")
//                .build();
//
//        user = User.builder()
//                .username("Luna")
//                .password("hahaha")
//                .build();
//    }
//
//    @AfterEach
//    public void afterEach() {
//        Mockito.reset(authService);
//    }
//
//    @Nested
//    class CreateUser {
//
//        @Nested
//        class WhenRequestIsValid {
//
//            @Test
//            public void should_create_new_user_and_SUCCESS() throws Exception{
//                when(authService.register(authRequestDto)).thenReturn("SUCCESS");
//
//                mockMvc.perform(post("/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(userJson.write(user).getJson()))
//                        .andExpect(status().isCreated());
//
//                verify(authService).register(authRequestDto);
//            }
//        }
//
//        @Nested
//        class WhenRequestIsNotValid {
//
//            @Test
//            public void should_not_create_user_and_return_Exception() throws Exception{
//
//                when(authService.register(authRequestDto)).thenThrow(new UserAlreadyExistException("User Already Exist, Please Try A New One! "));
//
//
//                mockMvc.perform(post("/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(userJson.write(user).getJson()))
//                        .andExpect(jsonPath("$.message",containsString("User Already Exist, Please Try A New One! ")))
//                        .andExpect(status().isBadRequest());
//
//                verify(authService,times(1)).register(authRequestDto);
//
//            }
//        }
//
//    }
//}
