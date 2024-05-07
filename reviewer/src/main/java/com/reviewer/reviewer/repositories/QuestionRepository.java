package com.reviewer.reviewer.repositories;

import com.reviewer.reviewer.models.Question;
import com.reviewer.reviewer.models.QuestionForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
