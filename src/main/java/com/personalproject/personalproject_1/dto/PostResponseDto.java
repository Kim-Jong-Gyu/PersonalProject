package com.personalproject.personalproject_1.dto;

import com.personalproject.personalproject_1.entitiy.PostEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {

    private Long id;
    private String postingName;
    private String userName;
    private String postingContent;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public PostResponseDto(PostEntity postEntity) {
        this.id = postEntity.getId();
        this.userName = postEntity.getUserName();
        this.postingName = postEntity.getPostingName();
        this.postingContent = postEntity.getPostingContent();
        this.createdAt = postEntity.getCreatedAt();
        this.modifiedAt = postEntity.getModifiedAt();
    }

}
