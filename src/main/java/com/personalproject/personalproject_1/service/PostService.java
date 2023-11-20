package com.personalproject.personalproject_1.service;

import com.personalproject.personalproject_1.dto.PostRequestDto;
import com.personalproject.personalproject_1.dto.PostResponseDto;
import com.personalproject.personalproject_1.entity.Post;
import com.personalproject.personalproject_1.entity.User;
import com.personalproject.personalproject_1.exception.ErrorCode;
import com.personalproject.personalproject_1.exception.Exception;
import com.personalproject.personalproject_1.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        Post post = new Post();
        post.setContent(requestDto.getContent());
        post.setTitle(requestDto.getTitle());
        post.setIsComplete(false);
        post.setUser(user);
        Post savePost = postRepository.save(post);
        return new PostResponseDto(savePost);
    }

    public PostResponseDto getPost(Long id) {
        return new PostResponseDto(findPost(id));
    }

    public Map<String, List<PostResponseDto>> getPosts() {
        Map<String, List<PostResponseDto>> userPostMap = new HashMap<>();
        List<PostResponseDto> postList = postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
        postList.forEach(postResponseDto -> {
            if(userPostMap.containsKey(postResponseDto.getUsername())){
                userPostMap.get(postResponseDto.getUsername()).add(postResponseDto);
            }
            else{
                userPostMap.put(postResponseDto.getUsername(), new ArrayList<>(List.of(postResponseDto)));
            }
        });
        return userPostMap;
    }


    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, User user) {
        Post post = findPost(id);
        if(Objects.equals(post.getUser().getId(), user.getId())){
            post.update(requestDto);
        }
        else {
            throw new Exception(ErrorCode.DO_NOT_MATCH_ID);
        }
        return new PostResponseDto(post);
    }
    @Transactional
    public void updateComplete(Long id, User user) {
        Post post = findPost(id);
        if(Objects.equals(post.getUser().getId(), user.getId())){
            post.complete();
        }
        else {
            throw new Exception(ErrorCode.DO_NOT_MATCH_ID);
        }
    }
//
//    public void deletePost(Long id, String password) {
//        Post post = findPost(id);
//        if (!post.getPassword().equals(password)) {
//            throw new Exception(ErrorCode.DO_NOT_MATCH_PASSWORD);
//        }
//        postRepository.delete(post);
//    }
//
//
    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new Exception(ErrorCode.NOT_FOUND_POST)
        );
    }
}
