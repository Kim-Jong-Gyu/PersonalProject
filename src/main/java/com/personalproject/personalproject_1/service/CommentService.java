package com.personalproject.personalproject_1.service;

import com.personalproject.personalproject_1.dto.CommentRequestDto;
import com.personalproject.personalproject_1.dto.CommentResponseDto;
import com.personalproject.personalproject_1.dto.PostRequestDto;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    public CommentResponseDto addComment(CommentRequestDto requestDto, User user) {
        Post post = postService.findPost(requestDto.getId());
        Comment comment = new Comment(requestDto);
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(CommentRequestDto requestDto, User user) {
        Comment comment = findComment(requestDto.getId());
        if(Objects.equals(comment.getUser().getId(), user.getId())){
            comment.update(requestDto);
        }
        else{
            throw new Exception(ErrorCode.DO_NOT_MATCH_ID);
        }
        return new CommentResponseDto(comment);
    }

    private Comment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new Exception(ErrorCode.NOT_FOUND_POST)
        );
    }
}
