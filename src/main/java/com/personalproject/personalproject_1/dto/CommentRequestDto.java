package com.personalproject.personalproject_1.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentRequestDto {
    //todo_id
    private Long id;
    private String text;
}
