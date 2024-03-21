package com.reviewer.reviewer.repositories;

import com.reviewer.reviewer.models.FormQuestion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionFormRepository extends JpaRepository<FormQuestion, Long> {
    List<FormQuestion> findAllByFormId(Long formId);
}
