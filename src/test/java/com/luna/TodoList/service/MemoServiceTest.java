package com.luna.TodoList.service;

import com.luna.TodoList.model.Memo;
import com.luna.TodoList.repository.MemoRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MemoServiceTest {

    @Mock
    MemoRepository memoRepository;

    private MemoService memoService;
    private Memo memo;

}
