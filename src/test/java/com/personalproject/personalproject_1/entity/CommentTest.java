package com.personalproject.personalproject_1.entity;

import com.personalproject.personalproject_1.dto.CommentRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    Long userId;
    Comment comment;
    @BeforeEach
    void setup(){
        comment = new Comment();
        userId = 1L;
    }
    @Test
    void setUser() {
        // given
        User user = new User();

        // when
        user.setUsername("jong");
        user.setId(userId);
        user.setPassword("1234");
        comment.setUser(user);

        // then
        assertEquals(userId, comment.getUser().getId());
    }

    @Test
    void update() {
        // given
        comment.setText("test 이전");
        CommentRequestDto commentRequestDto = new CommentRequestDto();
        commentRequestDto.setId(userId);
        commentRequestDto.setText("테스트입니다.");

        // when
        comment.update(commentRequestDto);

        // then
        assertEquals(comment.getText(), "테스트입니다.");
    }
}