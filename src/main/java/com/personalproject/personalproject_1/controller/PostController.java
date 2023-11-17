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

    @GetMapping("/{id}")
    public PostResponseDto getPosting(@PathVariable Long id) {
        return postService.getPosting(id);
    }
    @GetMapping
    public List<PostResponseDto> getPostings(){
        return postService.getPostings();
    }
    @PutMapping("/{id}/{password}")
    public PostResponseDto updatePosting(@PathVariable Long id, @PathVariable String password, @RequestBody PostRequestDto requestDto){
        return postService.updatePosting(id, password, requestDto);
    }
    @DeleteMapping("/{id}/{password}")
    public Long deletePosting(@PathVariable long id, @PathVariable String password){
        return postService.deletePosting(id,password);
    }
}
