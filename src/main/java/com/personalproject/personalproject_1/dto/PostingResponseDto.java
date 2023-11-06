package com.personalproject.personalproject_1.dto;

import com.personalproject.personalproject_1.entitiy.Posting;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostingResponseDto {

    private Long id;
    private String postingName;
    private String userName;
    private String postingContent;
    private String date;

    public PostingResponseDto(Posting posting) {
        this.id = posting.getId();
        this.userName = posting.getUserName();
        this.postingName = posting.getPostingName();
        this.postingContent = posting.getPostingContent();
        this.date = String.valueOf(posting.getDate());
    }

    public PostingResponseDto(Long id, String postingName, String postingContent, String userName, String date) {
        this.id = id;
        this.postingName = postingName;
        this.postingContent = postingContent;
        this.userName = userName;
        this.date = date;
    }
}
