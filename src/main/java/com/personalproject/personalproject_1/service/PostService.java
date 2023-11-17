package com.personalproject.personalproject_1.service;

import com.personalproject.personalproject_1.dto.PostRequestDto;
import com.personalproject.personalproject_1.dto.PostResponseDto;
import com.personalproject.personalproject_1.entitiy.PostEntity;
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

    public PostResponseDto createPosting(PostRequestDto requestDto) {
        PostEntity postEntity = new PostEntity(requestDto);
        PostEntity savePostEntity = postRepository.save(postEntity);
        return new PostResponseDto(savePostEntity);
    }

    public PostResponseDto getPosting(Long id) {
        return new PostResponseDto(findPosting(id));
    }

    public List<PostResponseDto> getPostings() {
        return postRepository.findAll().stream().map(PostResponseDto::new).toList();
    }


    @Transactional
    public PostResponseDto updatePosting(Long id, String password, PostRequestDto requestDto) {
        PostEntity postEntity = findPosting(id);
        System.out.println(postEntity.getPassword());
        if(!postEntity.getPassword().equals(password))
            throw new IllegalArgumentException("비밀번호를 잘못 입력했습니다.");
        postEntity.update(requestDto);
        return new PostResponseDto(postEntity);
    }

    public Long deletePosting(Long id, String password){
        PostEntity postEntity = findPosting(id);
        if(!postEntity.getPassword().equals(password))
            throw new IllegalArgumentException("비밀번호를 잘못 입력했습니다.");
        postRepository.delete(postEntity);
        return id;
    }


    private PostEntity findPosting(Long id){
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }

}
