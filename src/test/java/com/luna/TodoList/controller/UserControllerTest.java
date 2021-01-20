//package com.luna.TodoList.controller;
//
//import com.luna.TodoList.dto.UserRequestDto;
//import com.luna.TodoList.model.User;
//import com.luna.TodoList.service.UserService;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
////import static org.junit.Assert.assertThat;
//import static org.hamcrest.Matchers.*;
////import org.junit.Assert;
//
//import java.time.LocalDate;
//
//@WebMvcTest(UserController.class)
////@AutoConfigureJsonTesters
//@ExtendWith(SpringExtension.class)
//public class UserControllerTest {
//
//    @Autowired
//    private UserController userController;
//
//    @MockBean
//    private UserService userServiceMock;
//
//    @Mock
//    private UserService userService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
////    @BeforeEach
////    public void setUp(){
////        userRequestDto = UserRequestDto.builder()
////                .username("Luna")
////                .DateOfBirth(LocalDate.of(1995,11,18))
////                .email("unswluna@gmail.com")
////                .phone("18792626681")
////                .build();
////    }
////
////    @AfterEach
////    public void tearDown(){
////        Mockito.reset(userService);
////    }
//
//    @Nested
//    class PostUser{
//        @Test
//        public void should_create_user_success() throws Exception{
//            Long userId = (long)1;
//            String username = "Luna";
//            LocalDate DateOfBirth = LocalDate.of(1995,11,18);
//            String email = "unswlun@gmail.com";
//            String phone = "0412218970";
//
//            String userAsJson = "{ \"userId\" \"" + userId + "\", " +
//                    "\"username\": \"" + username + "\", " +
//                    "\"DateOfBirth\": \"" + DateOfBirth + "\", " +
//                    "\"email\": \"" + email + "\", " +
//                    "\"phone\": \"" + phone + "\", " +
//                    "}";
//
////            User user = new UserBuilder()
////                    .withUserId(userId)
////                    .withUsername(username)
////                    .withDateOfBirth(DateOfBirth)
////                    .withEmail(email)
////                    .withPhone(phone)
////                    .build();
//
//
//
//            UserRequestDto userRequestDto = UserRequestDto.builder()
//                    .username(username)
//                    .DateOfBirth(DateOfBirth)
//                    .email(email)
//                    .phone(phone)
//                    .build();
//
//            mockMvc.perform(
//                    post("/users")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(userAsJson))
//                    .andExpect(status().isCreated());
//
////            verify(userService).addUser(userRequestDto);
//        }
//    }
//
//    @Nested
//    class DeleteUser{
//        @Test
//        public void should_delete_user_success() throws Exception{
//            Long userId = (long)1;
//            String username = "Luna";
//            LocalDate DateOfBirth = LocalDate.of(1995,11,18);
//            String email = "unswlun@gmail.com";
//            String phone = "0412218970";
//
//            User user = userService.addUser(new UserRequestDto(username, DateOfBirth,email,phone));
//
//
//            mockMvc.perform(
//                    delete("/users/" + user.getUserId().toString()))
//                    .andExpect(status().isBadRequest());
//
////            User newUser = UserService.getUserById(user.getUserId());
////            assertThat(newUser).isNull();
//        }
//    }
//
//
//    @Nested
//    class getUserById{
//        @Test
//        public void should_return_user_success_when_get_by_user_id() throws Exception{
////            when(userService.getUserById(1L)).thenReturn(user);
////            assert (1 == 1);
//        }
//    }
//
//}
