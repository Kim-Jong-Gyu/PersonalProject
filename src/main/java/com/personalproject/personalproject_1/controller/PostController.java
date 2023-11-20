package com.personalproject.personalproject_1.controller;


import com.personalproject.personalproject_1.dto.PostRequestDto;
import com.personalproject.personalproject_1.dto.PostResponseDto;
import com.personalproject.personalproject_1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto){
        PostResponseDto postResponseDto = postService.createPost(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponseDto);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
        PostResponseDto postResponseDto = postService.getPost(postId);
        return ResponseEntity.ok(postResponseDto);
    }
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getPosts(){
        List<PostResponseDto> postResponseDto = postService.getPosts();
        return ResponseEntity.ok(postResponseDto);
    }
    @PatchMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto){
        PostResponseDto postResponseDto = postService.updatePost(postId,requestDto);
        return ResponseEntity.ok(postResponseDto);
    }


    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId, @RequestHeader("password") String password){
        postService.deletePost(postId,password);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
