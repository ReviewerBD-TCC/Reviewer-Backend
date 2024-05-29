package com.reviewer.reviewer.repositories;

import com.reviewer.reviewer.models.QuestionAnswer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {
    List<QuestionAnswer> findAllByForWhichUserId(String forWhichUserId);
    List<QuestionAnswer> findAllByUserId(String userId);

    List<QuestionAnswer> findAllByQuestionId(Long questionId);
}
