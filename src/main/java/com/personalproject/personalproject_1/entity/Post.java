package com.personalproject.personalproject_1.entity;

import com.personalproject.personalproject_1.dto.PostRequestDto;
import com.personalproject.personalproject_1.dto.PostResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


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

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    public Post(PostResponseDto postResponseDto) {
        this.id = postResponseDto.getId();
        this.title = postResponseDto.getTitle();
        this.content = postResponseDto.getContent();
        this.isComplete = false;
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void complete() {
        this.isComplete = true;
    }

}
