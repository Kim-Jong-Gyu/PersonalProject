package com.personalproject.personalproject_1.entitiy;

import com.personalproject.personalproject_1.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "posting")
@NoArgsConstructor
public class PostEntity extends TimeEntity {
    @Id
    // auto-increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "post_name", nullable = false)
    private String postingName;
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "content", nullable = false, length = 500)
    private String content;


    public PostEntity(PostRequestDto requestDto) {
        this.postingName = requestDto.getPostingName();
        this.userName = requestDto.getUserName();
        this.password = requestDto.getPassword();
        this.content = requestDto.getPostingContent();
    }
    public void update(PostRequestDto requestDto){
        this.postingName = requestDto.getPostingName();
        this.userName = requestDto.getUserName();
        this.content = requestDto.getPostingContent();
    }
}
