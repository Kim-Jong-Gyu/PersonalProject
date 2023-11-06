package com.personalproject.personalproject_1.controller;


import com.personalproject.personalproject_1.dto.PostingRequestDto;
import com.personalproject.personalproject_1.dto.PostingResponseDto;
import com.personalproject.personalproject_1.service.PostingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostingController {

    private final PostingService postingService;

    public PostingController(PostingService postingService){
        this.postingService = postingService;
    }

    @PostMapping("/posting")
    public PostingResponseDto createPosting(@RequestBody PostingRequestDto requestDto){
        return postingService.createPosting(requestDto);
    }

    @GetMapping("/posting/{id}")
    public PostingResponseDto getPosting(@PathVariable Long id) {
        return postingService.getPosting(id);
    }
    @GetMapping("/postings")
    public List<PostingResponseDto> getPostings(){
        return postingService.getPostings();
    }
    @PutMapping("/posting/{id}/{password}")
    public PostingResponseDto updatePosting(@PathVariable Long id, @PathVariable String password, @RequestBody PostingRequestDto requestDto){
        return postingService.updatePosting(id, password, requestDto);
    }
    @DeleteMapping("/posting/{id}/{password}")
    public Long deletePosting(@PathVariable long id, @PathVariable String password){
        return postingService.deletePosting(id,password);
    }
}
