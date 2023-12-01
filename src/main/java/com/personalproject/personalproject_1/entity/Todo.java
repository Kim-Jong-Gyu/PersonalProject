package com.personalproject.personalproject_1.entity;

import com.personalproject.personalproject_1.dto.TodoRequestDto;
import com.personalproject.personalproject_1.dto.TodoResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "todo")
@NoArgsConstructor
public class Todo extends Time {
    @Id
    // auto-increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "content", length = 500)
    private String content;
    @Column
    private Boolean isComplete;

    @OneToMany(mappedBy = "todo")
    private List<Comment> commentList = new ArrayList<>();

    public Todo(TodoResponseDto todoResponseDto) {
        this.id = todoResponseDto.getId();
        this.title = todoResponseDto.getTitle();
        this.content = todoResponseDto.getContent();
        this.isComplete = false;
    }

    public void update(TodoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void complete() {
        this.isComplete = true;
    }

}
