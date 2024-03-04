package com.reviewer.reviewer.repositories;

import com.reviewer.reviewer.models.QuestionForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionFormRepository extends JpaRepository<QuestionForm, Long> {
}
