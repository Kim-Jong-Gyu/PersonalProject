package com.personalproject.personalproject_1.service;

import com.personalproject.personalproject_1.dto.PostRequestDto;
import com.personalproject.personalproject_1.dto.PostResponseDto;
import com.personalproject.personalproject_1.entitiy.Post;
import com.personalproject.personalproject_1.exception.ErrorCode;
import com.personalproject.personalproject_1.exception.Exception;
import com.personalproject.personalproject_1.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        Post savePost = postRepository.save(post);
        return new PostResponseDto(savePost);
    }

    public PostResponseDto getPost(Long id) {
        return new PostResponseDto(findPost(id));
    }

    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }


    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = findPost(id);
        if (!post.getPassword().equals(requestDto.getPassword())) {
            throw new Exception(ErrorCode.DO_NOT_MATCH_PASSWORD);
        }
        post.update(requestDto);
        return new PostResponseDto(post);
    }

    public void deletePost(Long id, String password) {
        Post post = findPost(id);
        if (!post.getPassword().equals(password)) {
            throw new Exception(ErrorCode.DO_NOT_MATCH_PASSWORD);
        }
        postRepository.delete(post);
    }


    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new Exception(ErrorCode.NOT_FOUND_POST)
        );
    }
}
