package com.personalproject.personalproject_1.entitiy;

import com.personalproject.personalproject_1.dto.PostingRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class Posting {
    private long id;
    private String postingName;
    private String userName;
    private String password;
    private String postingContent;
    private LocalDate date;


    public Posting(PostingRequestDto requestDto) {
        this.postingName = requestDto.getPostingName();
        this.userName = requestDto.getUserName();
        this.password = requestDto.getPassword();
        this.postingContent = requestDto.getPostingContent();
    }
}
