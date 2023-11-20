package com.personalproject.personalproject_1.controller;

import com.personalproject.personalproject_1.dto.CommentRequestDto;
import com.personalproject.personalproject_1.dto.CommentResponseDto;
import com.personalproject.personalproject_1.dto.PostRequestDto;
import com.personalproject.personalproject_1.dto.PostResponseDto;
import com.personalproject.personalproject_1.entity.Comment;
import com.personalproject.personalproject_1.impl.UserDetailsImpl;
import com.personalproject.personalproject_1.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    @PostMapping
    public ResponseEntity<CommentResponseDto> addComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentRequestDto requestDto){
        CommentResponseDto commentResponseDto = commentService.addComment(requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDto);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody CommentRequestDto requestDto){
        CommentResponseDto commentResponseDto = commentService.updateComment(commentId, requestDto, userDetails.getUser());
        return ResponseEntity.ok(commentResponseDto);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.deleteComment(commentId, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
