package com.reviewer.reviewer.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.reviewer.reviewer.models.QuestionFormAnswer;

public interface QuestionFormAnswerRepository extends JpaRepository<QuestionFormAnswer, Long> {

}