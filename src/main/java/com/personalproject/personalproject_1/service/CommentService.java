package com.personalproject.personalproject_1.service;

import com.personalproject.personalproject_1.dto.CommentRequestDto;
import com.personalproject.personalproject_1.dto.CommentResponseDto;
import com.personalproject.personalproject_1.entity.Comment;
import com.personalproject.personalproject_1.entity.Todo;
import com.personalproject.personalproject_1.entity.User;
import com.personalproject.personalproject_1.exception.ErrorCode;
import com.personalproject.personalproject_1.exception.Exception;
import com.personalproject.personalproject_1.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TodoService todoService;
    public CommentResponseDto addComment(CommentRequestDto requestDto, User user) {
        Todo todo = todoService.findTodo(requestDto.getId());
        Comment comment = new Comment(requestDto);
        comment.setUser(user);
        comment.setTodo(todo);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto, User user) {
        Comment comment = findComment(commentId);
        if(Objects.equals(comment.getUser().getId(), user.getId())){
            comment.update(requestDto);
        }
        else{
            throw new Exception(ErrorCode.DO_NOT_MATCH_ID);
        }
        return new CommentResponseDto(comment);
    }

    public void deleteComment(Long commentId, User user) {
        Comment comment = findComment(commentId);
        if(!Objects.equals(comment.getUser().getId(), user.getId())){
            throw new Exception(ErrorCode.DO_NOT_MATCH_ID);
        }
        commentRepository.delete(comment);
    }

    private Comment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 Comment를 찾을수 없는니다.")
        );
    }

}
