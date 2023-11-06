package com.personalproject.personalproject_1.repository;

import com.personalproject.personalproject_1.entitiy.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostingRepository extends JpaRepository<Posting, Long> {
}
