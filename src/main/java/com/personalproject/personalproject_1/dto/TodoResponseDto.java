package com.personalproject.personalproject_1.dto;

import com.personalproject.personalproject_1.entity.Todo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TodoResponseDto {

    private Long id;
    private String title;
    private String username;
    private String content;
    private LocalDateTime createdAt;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.username = todo.getUser().getUsername();
        this.content = todo.getContent();
        this.createdAt = todo.getCreatedAt();
    }

}
