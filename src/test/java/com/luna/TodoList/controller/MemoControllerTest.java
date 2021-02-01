//package com.luna.TodoList.controller;
//
//import com.luna.TodoList.dto.MemoRequestDto;
//import com.luna.TodoList.exception.NotFoundException;
//import com.luna.TodoList.model.Memo;
//import com.luna.TodoList.service.MemoService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.json.JacksonTester;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static java.lang.Boolean.TRUE;
//import static org.assertj.core.api.Assertions.assertThat;
//
//import static java.lang.Boolean.FALSE;
//import static org.hamcrest.Matchers.*;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@WebMvcTest(MemoController.class)
//@AutoConfigureJsonTesters
//public class MemoControllerTest {
//
//    @MockBean
//    private MemoService memoService;
//
//    @Autowired
//    private JacksonTester<Memo> memoJson;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private Memo memo;
//    private MemoRequestDto memoRequestDto;
//    private MemoRequestDto memoRequestDtoWithoutMessage;
//    private List memoList;
//    private List memoEmptyList;
//
//    @Nested
//    class getMemo {
//
//        @BeforeEach
//        public void setUp(){
//            memo = Memo.builder()
//                    .id((long)1)
//                    .message("someMessage")
//                    .tag("Others")
//                    .complete(FALSE)
//                    .publicity(FALSE)
//                    .userId((long)2)
//                    .build();
//
//            memoList = new ArrayList<>();
//            memoEmptyList = new ArrayList<>();
//            memoList.add(memo);
//        }
//        @AfterEach
//        public void afterEach(){
//            Mockito.reset(memoService);
//        }
//
//        @Nested
//        class whenMemoIdExists {
//
//            @Test
//            public void should_return_memo_by_memoId_with_jsonPath() throws Exception {
//                when(memoService.getMemoById(1L)).thenReturn(memo);
//
//                mockMvc.perform(get("/memos/{id}",1L))
//                        .andExpect(status().isOk())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(jsonPath("$.message", is("someMessage")));
//
//                verify(memoService,times(1)).getMemoById(1L);
//
//            }
//
//            @Test
//            public void should_return_memo_by_memoId_with_jacksonTester() throws Exception{
//                when(memoService.getMemoById(1L)).thenReturn(memo);
//
//                MockHttpServletResponse response = mockMvc.perform(get("/memos/{id}",1L))
//                        .andExpect(status().isOk())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                        .andReturn()
//                        .getResponse();
//
//                assertThat(response.getContentAsString()).isEqualTo(
//                        memoJson.write(memo).getJson());
//
//                verify(memoService).getMemoById(1L);
//
//            }
//
//            @Test
//            public void should_return_all_memos_by_userId() throws Exception {
//                when(memoService.getMemoByUserId(1L)).thenReturn(memoList);
//
//                mockMvc.perform(get("/memos/users/{userId}",1L))
//                        .andExpect(status().isOk())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(jsonPath("$",hasSize(1)));
//
//                verify(memoService,times(1)).getMemoByUserId(1L);
//
//            }
//
//            @Test
//            public void should_return_all_memos_by_userId_with_empty_memos() throws Exception {
//                when(memoService.getMemoByUserId(1L)).thenReturn(memoEmptyList);
//
//                mockMvc.perform(get("/memos/users/{userid}",1L))
//                        .andExpect(status().isOk())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(jsonPath("$",hasSize(0)));
//
//                verify(memoService,times(1)).getMemoByUserId(1L);
//
//            }
//
//            @Test
//            public void should_return_memos_by_tag() throws Exception{
//                when(memoService.getMemosByTag("Luna")).thenReturn(memoList);
//
//                mockMvc.perform(get("/memos/tags/{tag}","Luna"))
//                        .andExpect(status().isOk())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(jsonPath("$",hasSize(1)));
//
//                verify(memoService,times(1)).getMemosByTag("Luna");
//
//            }
//
//            @Test
//            public void should_return_memos_by_keyword() throws Exception{
//                when(memoService.getMemosByKeyword("Luna")).thenReturn(memoList);
//
//                mockMvc.perform(get("/memos/keywords/{keyword}","Luna"))
//                        .andExpect(status().isOk())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(jsonPath("$",hasSize(1)));
//
//                verify(memoService,times(1)).getMemosByKeyword("Luna");
//
//            }
//
//            @Test
//            public void should_return_memos_by_complete() throws Exception{
//                when(memoService.getMemosByCompleted(TRUE)).thenReturn(memoList);
//
//                mockMvc.perform(get("/memos/complete")
//                        .param("completed", String.valueOf(TRUE)))
//                        .andExpect(status().isOk())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(jsonPath("$",hasSize(1)));
//
//                verify(memoService,times(1)).getMemosByCompleted(TRUE);
//
//            }
//
//        }
//
//        @Nested
//        class whenMemoIdNotExists {
//
//            @Test
//            public void Should_return_NOT_FOUND () throws  Exception {
//                when(memoService.getMemoById(1L)).thenThrow(new NotFoundException("Memo Not Found"));
//
//                mockMvc.perform(get("/memos/{id}",1L))
//                        .andExpect(status().isNotFound())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(jsonPath("$.message", containsString("Memo Not Found")));
//
//                verify(memoService,times(1)).getMemoById(1L);
//            }
//
//            @Test
//            public void should_return_memos_by_NOT_EXIST_tag() throws Exception{
//                when(memoService.getMemosByTag("Luna")).thenThrow(new NotFoundException("Tag not exist"));
//
//                mockMvc.perform(get("/memos/tags/{tag}","Luna"))
//                        .andExpect(status().isNotFound())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(jsonPath("$.message",containsString("Tag not exist")));
//
//                verify(memoService,times(1)).getMemosByTag("Luna");
//
//            }
//
//            @Test
//            public void should_return_memos_by_NOT_EXIST_keyword() throws Exception{
//                when(memoService.getMemosByKeyword("Luna")).thenThrow(new NotFoundException("Keyword not exist"));
//
//                mockMvc.perform(get("/memos/keywords/{keyword}","Luna"))
//                        .andExpect(status().isNotFound())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(jsonPath("$.message",containsString("Keyword not exist")));
//
//                verify(memoService,times(1)).getMemosByKeyword("Luna");
//
//            }
//        }
//    }
//
//    @Nested
//    class CreateMemo {
//        @BeforeEach
//        public void setUp(){
//            memoRequestDto = MemoRequestDto.builder()
//                    .message("someMessage")
//                    .tag("Others")
//                    .complete(FALSE)
//                    .publicity(FALSE)
//                    .userId((long)2)
//                    .build();
//            memoRequestDtoWithoutMessage = MemoRequestDto.builder()
//                    .tag("Others")
//                    .complete(FALSE)
//                    .publicity(FALSE)
//                    .userId((long)2)
//                    .build();
//
//            memo = Memo.builder()
//                    .id((long)1)
//                    .message("someMessage")
//                    .tag("Others")
//                    .complete(FALSE)
//                    .publicity(FALSE)
//                    .userId((long)2)
//                    .build();
//        }
//        @AfterEach
//        public void afterEach(){
//            Mockito.reset(memoService);
//        }
//
//        @Nested
//        class WhenRequestIsValid {
//
//            @Test
//            public void should_create_new_memo_and_return_json() throws Exception {
//                when(memoService.addMemo(memoRequestDto)).thenReturn(memo);
//
//                MockHttpServletRequestBuilder requestBuilder = post("/memos")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(memoJson.write(memo).getJson());
//
//                MockHttpServletResponse response = mockMvc.perform(requestBuilder)
//                        .andReturn()
//                        .getResponse();
//
//                assertThat(response.getStatus()).isEqualTo(
//                        HttpStatus.CREATED.value());
//
//                verify(memoService).addMemo(memoRequestDto);
//
//            }
//        }
//
//        @Nested
//        class WhenRequestIsNotValid {
//
//            @Test
//            public void should_not_create_new_memo_and_return_400() throws Exception {
//                //todo: 1. how to ?? 2.意愿：违反 @valid. such as no message.
//
//                mockMvc.perform(post("/memos"))
//                        .andExpect(status().isBadRequest());
//
//                verify(memoService,times(0)).addMemo(memoRequestDtoWithoutMessage);
//
//            }
//
//        }
//
//
//    }
//
//    @Nested
//    class DeleteMemo {
//
//        @Nested
//        class whenMemoExists {
//
//            @Test
//            public void should_delete_memo_by_memoId() throws Exception {
//
//                when(memoService.deleteMemosById(1L)).thenReturn("SUCCESS");
//
//                mockMvc.perform(MockMvcRequestBuilders.delete("/memos/{id}", 1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                        .andExpect(status().isOk());
//
//                verify(memoService,times(1)).deleteMemosById(1L);
//            }
//
//            @Test
//            public void should_delete_memo_by_userId() throws Exception {
//                when(memoService.deleteMemosByUserId(1L)).thenReturn("SUCCESS");
//
//                mockMvc.perform(MockMvcRequestBuilders.delete("/memos/users/{userId}", 1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                        .andExpect(status().isOk());
//
//                verify(memoService,times(1)).deleteMemosByUserId(1L);
//
//            }
//        }
//
//        @Nested
//        class whenMemoNotExists {
//
//            @Test
//            public void should_return_400_when_delete_by_memoId_if_memo_not_exist() throws Exception {
//
//                when(memoService.deleteMemosById(1L)).thenThrow(new NotFoundException("Memo Not Found"));
//
//                mockMvc.perform(MockMvcRequestBuilders.delete("/memos/{id}", 1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                        .andExpect(jsonPath("$.message", containsString("Memo Not Found")))
//                        .andExpect(status().isNotFound());
//
//                verify(memoService,times(1)).deleteMemosById(1L);
//
//            }
//
//            @Test
//            public void should_return_400_when_delete_by_userId_if_user_not_exist() throws Exception {
//
//                when(memoService.deleteMemosByUserId(1L)).thenThrow(new NotFoundException("User Not Found"));
//
//                mockMvc.perform(MockMvcRequestBuilders.delete("/memos/users/{id}", 1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                        .andExpect(jsonPath("$.message", containsString("User Not Found")))
//                        .andExpect(status().isNotFound());
//
//                verify(memoService,times(1)).deleteMemosByUserId(1L);
//
//            }
//
//        }
//
//    }
//
//    @Nested
//    class PutMemo {
//
//        @BeforeEach
//        public void setUp(){
//            memoRequestDto = MemoRequestDto.builder()
//                    .message("someMessage")
//                    .tag("Others")
//                    .complete(FALSE)
//                    .publicity(FALSE)
//                    .userId((long)2)
//                    .build();
//            memoRequestDtoWithoutMessage = MemoRequestDto.builder()
//                    .tag("Others")
//                    .complete(FALSE)
//                    .publicity(FALSE)
//                    .userId((long)2)
//                    .build();
//
//            memo = Memo.builder()
//                    .id((long)1)
//                    .message("someMessage")
//                    .tag("Others")
//                    .complete(FALSE)
//                    .publicity(FALSE)
//                    .userId((long)2)
//                    .build();
//        }
//
//        @AfterEach
//        public void afterEach(){
//            Mockito.reset(memoService);
//        }
//
//        @Nested
//        class whenMemoExist {
//
//            @Test
//            public void should_update_memo_by_id() throws Exception {
//                when(memoService.updateMemo(1L,memoRequestDto)).thenReturn(memo);
//
//                mockMvc.perform(put("/memos/{id}",1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(memoJson.write(memo).getJson())
//                        .accept(MediaType.APPLICATION_JSON))
//                        .andExpect(jsonPath("$.message",containsString("someMessage")))
//                        .andExpect(status().isAccepted());
//
//                verify(memoService,times(1)).updateMemo(1L,memoRequestDto);
//            }
//
//
//        }
//
//        @Nested
//        class whenMemoNotExist {
//            @Test
//            public void should_not_update_memo_if_memo_not_exist() throws Exception {
//                when(memoService.updateMemo(1L,memoRequestDto)).thenThrow(new NotFoundException("MEMO NOT EXIST"));
//
//                mockMvc.perform(put("/memos/{id}",1L))
//                        .andExpect(status().isBadRequest());
////                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
////                        .andExpect(jsonPath("$.message", containsString("MEMO NOT EXIST")));
//
//                verify(memoService,times(0)).updateMemo(1L,memoRequestDto);
//                //todo: why is o times? should be 1? something wrong? why not call updateMemo
//            }
//        }
//    }
//}
//
