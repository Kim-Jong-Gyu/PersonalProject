package com.personalproject.personalproject_1.controller;


import com.personalproject.personalproject_1.dto.PostRequestDto;
import com.personalproject.personalproject_1.dto.PostResponseDto;
import com.personalproject.personalproject_1.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto){
        return postService.createPost(requestDto);
    }

    @GetMapping("/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }
    @GetMapping
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }
    @PutMapping("/{postId}/{password}")
    public PostResponseDto updatePost(@PathVariable Long postId, @PathVariable String password, @RequestBody PostRequestDto requestDto){
        return postService.updatePost(postId, password, requestDto);
    }
    @DeleteMapping("/{postId}/{password}")
    public Long deletePost(@PathVariable Long postId, @PathVariable String password){
        return postService.deletePost(postId,password);
    }
}
