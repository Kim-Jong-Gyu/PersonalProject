package com.personalproject.personalproject_1.dto;

import lombok.Getter;

import java.time.LocalTime;
@Getter
public class PostingRequestDto {
    private String postingName;
    private String userName;
    private String password;
    private String postingContent;
}
