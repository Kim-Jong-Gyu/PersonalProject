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
    public PostResponseDto createPosting(@RequestBody PostRequestDto requestDto){
        return postService.createPosting(requestDto);
    }

    @GetMapping("/{postId}")
    public PostResponseDto getPosting(@PathVariable Long postId) {
        return postService.getPosting(postId);
    }
    @GetMapping
    public List<PostResponseDto> getPostings(){
        return postService.getPostings();
    }
    @PutMapping("/{postId}/{password}")
    public PostResponseDto updatePosting(@PathVariable Long postId, @PathVariable String password, @RequestBody PostRequestDto requestDto){
        return postService.updatePosting(postId, password, requestDto);
    }
    @DeleteMapping("/{postId}/{password}")
    public Long deletePosting(@PathVariable Long postId, @PathVariable String password){
        return postService.deletePosting(postId,password);
    }
}
