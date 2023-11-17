package com.personalproject.personalproject_1.repository;

import com.personalproject.personalproject_1.entitiy.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
