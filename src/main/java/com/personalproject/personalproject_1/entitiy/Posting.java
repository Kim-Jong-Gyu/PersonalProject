package com.personalproject.personalproject_1.entitiy;

import com.personalproject.personalproject_1.dto.PostingRequestDto;
import com.personalproject.personalproject_1.dto.PostingResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "posting")
@NoArgsConstructor
public class Posting extends Timestamped {
    @Id
    // auto-increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "postingName", nullable = false, unique = true)
    private String postingName;
    @Column(name = "userName", nullable = false)
    private String userName;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "postingContent", nullable = false, length = 500)
    private String postingContent;


    public Posting(PostingRequestDto requestDto) {
        this.postingName = requestDto.getPostingName();
        this.userName = requestDto.getUserName();
        this.password = requestDto.getPassword();
        this.postingContent = requestDto.getPostingContent();
    }
    public void update(PostingRequestDto requestDto){
        this.postingName = requestDto.getPostingName();
        this.userName = requestDto.getUserName();
        this.postingContent = requestDto.getPostingContent();
    }
}
