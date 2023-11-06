package com.personalproject.personalproject_1.service;

import com.personalproject.personalproject_1.dto.PostingRequestDto;
import com.personalproject.personalproject_1.dto.PostingResponseDto;
import com.personalproject.personalproject_1.entitiy.Posting;
import com.personalproject.personalproject_1.repository.PostingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostingService {

    private final PostingRepository postingRepository;

    public PostingService(PostingRepository postingRepository) {
        this.postingRepository = postingRepository;
    }

    public PostingResponseDto createPosting(PostingRequestDto requestDto) {
        Posting posting = new Posting(requestDto);
        Posting savePosting = postingRepository.save(posting);
        return new PostingResponseDto(savePosting);
    }

    public PostingResponseDto getPosting(Long id) {
        return new PostingResponseDto(findPosting(id));
    }

    public List<PostingResponseDto> getPostings() {
        return postingRepository.findAll().stream().map(PostingResponseDto::new).toList();
    }


    @Transactional
    public PostingResponseDto updatePosting(Long id, String password, PostingRequestDto requestDto) {
        Posting posting = findPosting(id);
        System.out.println(posting.getPassword());
        if(!posting.getPassword().equals(password))
            throw new IllegalArgumentException("비밀번호를 잘못 입력했습니다.");
        posting.update(requestDto);
        return new PostingResponseDto(posting);
    }

    public Long deletePosting(Long id, String password){
        Posting posting = findPosting(id);
        if(!posting.getPassword().equals(password))
            throw new IllegalArgumentException("비밀번호를 잘못 입력했습니다.");
        postingRepository.delete(posting);
        return id;
    }


    private Posting findPosting(Long id){
        return postingRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }

}
