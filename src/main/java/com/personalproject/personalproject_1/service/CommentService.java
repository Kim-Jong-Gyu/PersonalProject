package com.personalproject.personalproject_1.service;

import com.personalproject.personalproject_1.dto.CommentRequestDto;
import com.personalproject.personalproject_1.dto.CommentResponseDto;
import com.personalproject.personalproject_1.dto.PostResponseDto;
import com.personalproject.personalproject_1.entity.Comment;
import com.personalproject.personalproject_1.entity.Post;
import com.personalproject.personalproject_1.entity.User;
import com.personalproject.personalproject_1.exception.ErrorCode;
import com.personalproject.personalproject_1.exception.Exception;
import com.personalproject.personalproject_1.repository.CommentRepository;
import com.personalproject.personalproject_1.repository.PostRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    public CommentResponseDto addComment(CommentRequestDto requestDto, User user) {
        PostResponseDto postResponseDto = postService.getPost(requestDto.getId());
        Comment comment = new Comment(requestDto);
        comment.setUser(user);
        comment.setPost(new Post(postResponseDto));
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }
}
