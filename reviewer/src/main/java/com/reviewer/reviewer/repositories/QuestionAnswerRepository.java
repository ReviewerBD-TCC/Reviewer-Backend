package com.reviewer.reviewer.repositories;

import com.reviewer.reviewer.models.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {
}
