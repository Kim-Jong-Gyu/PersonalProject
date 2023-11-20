package com.personalproject.personalproject_1.entity;

import com.personalproject.personalproject_1.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "posts")
@NoArgsConstructor
public class Post extends Time {
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

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
