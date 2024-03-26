package com.reviewer.reviewer.repositories;

import com.reviewer.reviewer.models.QuestionForm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionFormRepository extends JpaRepository<QuestionForm, Long> {
    List<QuestionForm> findAllByFormId(Long formId);
}
