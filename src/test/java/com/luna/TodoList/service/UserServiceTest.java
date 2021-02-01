//package com.luna.TodoList.service;
//
//import com.luna.TodoList.dto.UserRequestDto;
//import com.luna.TodoList.exception.NotFoundException;
//import com.luna.TodoList.model.User;
//import com.luna.TodoList.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.time.LocalDate;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//
//    @Mock
//    UserRepository userRepository;
//
//    private UserService userService;
//    private User user;
//    private UserRequestDto userRequestDto;
//
//
//    @BeforeEach
//    void setUp() {
//        userService = new UserService(userRepository);
//
//        user = User.builder()
//                .userId((long)1)
//                .username("Luna")
//                .DateOfBirth(LocalDate.of(1995,11,18))
//                .email("unswlun@gmail.com")
//                .phone("0412218970")
//                .build();
//
//        userRequestDto = UserRequestDto.builder()
//                .username("Luna")
//                .DateOfBirth(LocalDate.of(1995,11,18))
//                .email("unswlun@gmail.com")
//                .phone("0412218970")
//                .build();
//    }
//
//    @Nested
//    class FindById {
//
//        @Nested
//        class WhenIdExists {
//
//            @Test
//            public void should_return_user() throws Exception{
//                when(userRepository.findById(1l)).thenReturn(Optional.of(user));
//
//                User foundUser = userService.getUserById(1L);
//
//                assertThat(foundUser).isEqualTo(User.builder()
//                        .userId((long)1)
//                        .username("Luna")
//                        .DateOfBirth(LocalDate.of(1995,11,18))
//                        .email("unswlun@gmail.com")
//                        .phone("0412218970")
//                        .build());
//
//                verify(userRepository).findById(1L);
//
//            }
//        }
//
//        @Nested
//        class WhenUserIdNotExisted {
//
//            @Test
//            void should_throw_exception() {
//                when(userRepository.findById(1L)).thenReturn(Optional.empty());
//
//                NotFoundException thrownException = assertThrows(NotFoundException.class, () -> {
//                    userService.getUserById(1L);
//                });
//
//                assertThat(thrownException.getMessage()).containsSequence("User not exit");
//            }
//        }
//    }
//
//    @Nested
//    class CreateUser {
//
//        @Nested
//        class whenDtoCorrect {
//
//            //              https://www.freecodecamp.org/news/unit-testing-services-endpoints-and-repositories-in-spring-boot-4b7d9dc2b772/
////            @Test
////            public void should_create_user() {
////                User userToSet = new User();
////                userToSet.setUsername("Luna");
////                when(userRepository.save(user)).thenReturn(user);
////
////                User foundUser = userService.addUser(userRequestDto);
////
////                assertThat(foundUser.getUsername()).isSameAs(userToSet.getUsername());
////                verify(userRepository).save(user);
////
////            }
//        }
//    }
//}
