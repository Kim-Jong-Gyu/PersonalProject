package com.personalproject.personalproject_1.service;

import com.personalproject.personalproject_1.dto.CommentRequestDto;
import com.personalproject.personalproject_1.dto.CommentResponseDto;
import com.personalproject.personalproject_1.entity.Comment;
import com.personalproject.personalproject_1.entity.Todo;
import com.personalproject.personalproject_1.entity.User;
import com.personalproject.personalproject_1.exception.CommentNotFoundException;
import com.personalproject.personalproject_1.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TodoService todoService;
    private final MessageSource messageSource;

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
        if (todoService.matchUser(comment.getUser().getId(), user.getId())) {
            comment.update(requestDto);
        }
        return new CommentResponseDto(comment);
    }

    public void deleteComment(Long commentId, User user) {
        Comment comment = findComment(commentId);
        if (todoService.matchUser(comment.getUser().getId(), user.getId())) {
            commentRepository.delete(comment);
        }
    }

    private Comment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new CommentNotFoundException(messageSource.getMessage(
                        "not.found.comment",
                        null,
                        "Not found Comment",
                        Locale.getDefault()
                ))
        );
    }

}
