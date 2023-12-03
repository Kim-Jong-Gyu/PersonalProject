package com.personalproject.personalproject_1.entity;

import static org.junit.jupiter.api.Assertions.*;

import com.personalproject.personalproject_1.dto.TodoRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TodoTest {

    Todo todo;
    @BeforeEach
    void setUp(){
        todo = new Todo();
        String title = "테스트 제목입니다.";
        String content = "테스트 내용입니다.";
        Boolean isComplete = false;
        User user = new User("jong", "1234");
        todo.setUser(user);
        todo.setTitle(title);
        todo.setContent(content);
        todo.setIsComplete(isComplete);
    }

    @Test
    @DisplayName("Todo update 테스트")
    void makeTodo(){
        // given
        TodoRequestDto todoRequestDto = new TodoRequestDto();
        String title = "수정 제목입니다.";
        String content = "수정 내용입니다.";
        todoRequestDto.setTitle(title);
        todoRequestDto.setContent(content);

        // when
        todo.update(todoRequestDto);

        // then
        assertEquals(todo.getTitle(), title);
        assertEquals(todo.getContent(), content);
    }

    @Test
    @DisplayName("Complete 변화 테스트")
    void completeChange(){
        // when
        todo.complete();

        // then
        assertEquals(todo.getIsComplete(),true);
    }

}