package com.reviewer.reviewer.repositories;

import com.reviewer.reviewer.models.Question;
import org.springdoc.core.providers.JavadocProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}