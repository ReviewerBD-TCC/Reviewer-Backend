package com.reviewer.reviewer.repositories;

import com.reviewer.reviewer.models.QuestionAnswer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {
    List<QuestionAnswer> findByUserId(Long userId);
}
