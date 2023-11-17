package com.personalproject.personalproject_1.service;

import com.personalproject.personalproject_1.dto.PostRequestDto;
import com.personalproject.personalproject_1.dto.PostResponseDto;
import com.personalproject.personalproject_1.entitiy.Post;
import com.personalproject.personalproject_1.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

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
            throw new IllegalArgumentException("비밀번호를 잘못 입력했습니다.");
        }
        post.update(requestDto);
        return new PostResponseDto(post);
    }

    public Long deletePost(Long id, String password) {
        Post post = findPost(id);
        if (!post.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호를 잘못 입력했습니다.");
        }
        postRepository.delete(post);
        return id;
    }


    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
}
