//package com.luna.TodoList.APITest;
//
//import com.luna.TodoList.controller.MemoController;
//import com.luna.TodoList.dto.MemoRequestDto;
//import com.luna.TodoList.model.Memo;
//import com.luna.TodoList.repository.MemoRepository;
//import com.luna.TodoList.service.MemoService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.json.JacksonTester;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Optional;
//
//import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = NONE)
//@AutoConfigureJsonTesters
//public class MemoTest {
//
//    @Autowired
//    MemoRepository memoRepository;
//
//    @Autowired
//    MemoService memoService;
//
//    @Autowired
//    MemoController memoController;
//
//    @Autowired
//    private JacksonTester<Memo> memoJson;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void share_memo_thought_userId() {
//
//        // given
//        Long userId2 = 2L;
//        Memo memo = new Memo();
//        memo.setMessage("This is a test message");
//        memo.setUserId(1L);
//
//        memoRepository.save(memo);
//        memoService.shareMemo(userId2,memo);
//
//        //when
//        Optional<Memo> memo_to_check = memoRepository.findById(userId2);
//
//        //then
//        assertThat(memoRepository.findById(1L)).isEqualTo(memo_to_check);
//
//    }
//
//}
