package com.personalproject.personalproject_1.controller;


import com.personalproject.personalproject_1.dto.PostRequestDto;
import com.personalproject.personalproject_1.dto.PostResponseDto;
import com.personalproject.personalproject_1.exception.Exception;
import com.personalproject.personalproject_1.exception.ExceptionResponseDto;
import com.personalproject.personalproject_1.impl.UserDetailsImpl;
import com.personalproject.personalproject_1.service.PostService;
import com.personalproject.personalproject_1.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    private final JwtUtil jwtUtil;
    public static final String AUTHORIZATION_HEADER = "Authorization";

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto requestDto){
        PostResponseDto postResponseDto = postService.createPost(requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponseDto);
    }

//    @GetMapping("/{postId}")
//    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
//        PostResponseDto postResponseDto = postService.getPost(postId);
//        return ResponseEntity.ok(postResponseDto);
//    }
//    @GetMapping
//    public ResponseEntity<List<PostResponseDto>> getPosts(){
//        List<PostResponseDto> postResponseDto = postService.getPosts();
//        return ResponseEntity.ok(postResponseDto);
//    }
//    @PatchMapping("/{postId}")
//    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto){
//        PostResponseDto postResponseDto = postService.updatePost(postId,requestDto);
//        return ResponseEntity.ok(postResponseDto);
//    }
//
//
//    @DeleteMapping("/{postId}")
//    public ResponseEntity<Void> deletePost(@PathVariable Long postId, @RequestHeader("password") String password){
//        postService.deletePost(postId,password);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> exceptionHandler(Exception e){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(e.getErrorCode());
        return ResponseEntity.status(exceptionResponseDto.getStatus()).body(exceptionResponseDto);
    }
}
