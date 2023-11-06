package com.personalproject.personalproject_1.service;

import com.personalproject.personalproject_1.dto.PostingRequestDto;
import com.personalproject.personalproject_1.dto.PostingResponseDto;
import com.personalproject.personalproject_1.entitiy.Posting;
import com.personalproject.personalproject_1.repository.PostingRepository;
import org.springframework.stereotype.Service;

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
        LocalDate localDate = LocalDate.now();
        posting.setDate(localDate);
        Posting savePosting = postingRepository.save(posting);
        return new PostingResponseDto(savePosting);
    }

    public PostingResponseDto getPosting(Long id) {
        return new PostingResponseDto(postingRepository.findById(id, null));
    }

    public List<PostingResponseDto> getPostings() {
        return postingRepository.findAll();
    }

    public PostingResponseDto updatePosting(Long id, String password, PostingRequestDto requestDto) {
        Posting posting = postingRepository.findById(id,password);
        if(posting != null){
            postingRepository.update(id, requestDto);
            return getPosting(id);
        }
        else{
            throw new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.");
        }
    }

    public Long deletePosting(long id, String password) {
        Posting posting = postingRepository.findById(id, password);
        if(posting != null){
            postingRepository.delete(id);
            return id;
        }
        else{
            throw new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.");
        }
    }
}
