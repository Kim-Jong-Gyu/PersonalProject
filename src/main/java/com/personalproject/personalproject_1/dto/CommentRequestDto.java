package com.personalproject.personalproject_1.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentRequestDto {
    //todo_id
    private Long id;
    private String text;
}
