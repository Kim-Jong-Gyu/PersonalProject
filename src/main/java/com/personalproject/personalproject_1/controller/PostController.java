package com.personalproject.personalproject_1.controller;


import com.personalproject.personalproject_1.dto.PostRequestDto;
import com.personalproject.personalproject_1.dto.PostResponseDto;
import com.personalproject.personalproject_1.exception.Exception;
import com.personalproject.personalproject_1.exception.ExceptionResponseDto;
import com.personalproject.personalproject_1.impl.UserDetailsImpl;
import com.personalproject.personalproject_1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto requestDto){
        PostResponseDto postResponseDto = postService.createPost(requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponseDto);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
        PostResponseDto postResponseDto = postService.getPost(postId);
        return ResponseEntity.ok(postResponseDto);
    }
    @GetMapping
    public ResponseEntity<Map<String, List<PostResponseDto>>> getPosts(){
        Map<String, List<PostResponseDto>> postResponseDto = postService.getPosts();
        return ResponseEntity.ok(postResponseDto);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId, @RequestBody PostRequestDto requestDto){
        PostResponseDto postResponseDto = postService.updatePost(postId,requestDto, userDetails.getUser());
        return ResponseEntity.ok(postResponseDto);
    }

    @PatchMapping("/completed/{postId}")
    public ResponseEntity<Void> updateComplete(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId){
        postService.updateComplete(postId, userDetails.getUser());
        return ResponseEntity.ok().build();
    }
    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> exceptionHandler(Exception e){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(e.getErrorCode());
        return ResponseEntity.status(exceptionResponseDto.getStatus()).body(exceptionResponseDto);
    }
}
