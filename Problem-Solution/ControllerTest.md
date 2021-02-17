1. 能否使用主文件中的 users builder
现：在test文档中新建了Builder文件夹存放相关文档

2. controller 层测试内容
现verify(userService).addUser(userRequestDto);不需要？

3.测试的涵盖面
例如：create a users：
success：ok,endwith（"/users/1")
fail
1. mock controller, 确保调用了service的方法。
2. 验证输出：controller返回格式是否符合需求（json，404）

结果：
1 service层是否被调用
2 返回值（状态码，json格式，序列化是否正确）

===> 测职责，而不是具体实现

??? to ask: 测试delete:
choice1: 有个repo，测试删除后只有一个
choice2: delete被调用，返回"success"（mock），how？if return void

～～～～～～～～～～ 简单小说明 ～～～～～～～～～～～
1 简单说明
@WebMvcTest(MemoController.class)
相比启动springboot全套，只启动具体的controller，加载更快

@MockBean
private MemoService memoServiceMock;
因为我只启动的MemoController，会检测到service为启动，然后启动它，加入controller中

测试小问题：
1. Springboot controller -> delete function test.
测试：WhenUserExist: should_delete_user_by_id 
报错：Status expected:<200> but was:<400>
原因: User controller 代码中错误将 @ResponseStatus(HttpStatus.OK) 写成 @ResponseStatus(HttpStatus.BAD_REQUEST)]

2. controller --> @ResponseStatus状态码 & static
测试: getUser: should_return_user_by_id
报错1: java.lang.NullPointerException
原因: service层 getUserById 错用 static
报错2: Status expected:<200> but was:<202>
原因: @ResponseStatus状态码错用

3. `Wanted but not invoked:
   com.luna.TodoList.service.MemoService#0 bean.updateMemo(
       1L,
       MemoRequestDto(message=someMessage, tag=Others, complete=false, publicity=false, userId=2)
   );
   -> at com.luna.TodoList.controller.MemoControllerTest$PutMemo$whenMemoNotExist.should_not_update_memo_if_user_not_exist(MemoControllerTest.java:333)
   Actually, there were zero interactions with this mock.` <br> 
   原因：verify(memoService,times(1)).updateMemo(1L,memoRequestDto); 

4. Missing URI template variable 'id' for method parameter of type Long <br> Expected :200                                                                    Actual   :500 <br>
错误原因：问题出现的原因是使用@PathVariable注解而没有采用rest的写法 
更改：1. @GetMapping("users/{userId}") ==> @GetMapping("/users/{userId}") 
2. (@PathVariable Long id) ==> (@PathVariable Long userId)

