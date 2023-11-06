package com.personalproject.personalproject_1.dto;

import com.personalproject.personalproject_1.entitiy.Posting;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class PostingResponseDto {

    private Long id;
    private String postingName;
    private String userName;
    private String postingContent;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public PostingResponseDto(Posting posting) {
        this.id = posting.getId();
        this.userName = posting.getUserName();
        this.postingName = posting.getPostingName();
        this.postingContent = posting.getPostingContent();
        this.createdAt = posting.getCreatedAt();
        this.modifiedAt = posting.getModifiedAt();
    }

}
