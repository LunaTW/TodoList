//package com.luna.TodoList.controller;
//
//import com.luna.TodoList.dto.MemoRequestDto;
//import com.luna.TodoList.dto.UserRequestDto;
//import com.luna.TodoList.model.Memo;
//import com.luna.TodoList.service.MemoService;
//import com.luna.TodoList.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//import javax.validation.constraints.NotNull;
//import java.time.LocalDate;
//
//import static java.lang.Boolean.FALSE;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(MemoController.class)
//@ExtendWith(SpringExtension.class)
//public class MemoControllerTest {
//
//    @Autowired
//    private MemoController memoController;
//
//    @MockBean
//    private MemoService memoServiceMock;
//
//    @Mock
//    private MemoService memoService;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private Memo memo;
//    private MemoRequestDto memoRequestDto;
//    private String memoAsJson;
//
//
//    @BeforeEach
//    public void setUp(){
//        long id = (long)1;
//        String message = "someMessage";
//        String tag = "Others";
//        Boolean complete = FALSE;
//        Boolean publicity = FALSE;
//        long userId = ((long)1);
//
//        memoRequestDto = MemoRequestDto.builder()
//                .message("someMessage")
//                .tag("Others")
//                .complete(FALSE)
//                .publicity(FALSE)
//                .userId((long)2)
//                .build();
//
//        memo = Memo.builder()
//                .id((long)1)
//                .message("someMessage")
//                .tag("Others")
//                .complete(FALSE)
//                .publicity(FALSE)
//                .userId((long)2)
//                .build();
//
//
//        memoAsJson = "{ \"id\" \"" + id + "\", " +
//                "\"message\": \"" + message + "\", " +
//                "\"tag\": \"" + tag + "\", " +
//                "\"complete\": \"" + complete + "\", " +
//                "\"publicity\": \"" + publicity + "\", " +
//                "\"localDate_created\": \"" + LocalDate.now() + "\", " +
//                "\"localDate_modified\": \"" + LocalDate.now() + "\", " +
//                "\"userId\": \"" + userId + "\", " +
//                "}";
//        }
//
//
//    @Test
//    public void should_create_memo_success() throws Exception {
////        when(memoService.addMemo(memoRequestDto)).thenReturn(memo);
////        ResponseEntity<Long> responseEntity = restTemplate.postForEntity("/memos", memoRequestDto, Long.class);
////        assertThat(responseEntity.getBody()).isEqualTo(memoAsJson);
//
//
//        mockMvc.perform(
//                post("/memos")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(memoAsJson))
//                .andExpect(status().isCreated());
//    }
//
//
//}
