package com.reviewer.reviewer.infra.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.reviewer.reviewer.dto.questions.QuestionAnswerDto;
import com.reviewer.reviewer.infra.exceptions.QuestionException;
import com.reviewer.reviewer.infra.exceptions.UserException;
import com.reviewer.reviewer.repositories.QuestionAnswerRepository;
import com.reviewer.reviewer.repositories.QuestionFormRepository;
import com.reviewer.reviewer.repositories.UserRepository;

public record ValidationQuestionAnswer (QuestionAnswerDto data){


    public void UserNotFound(UserRepository repository){
        if(repository.findById(data.userId().getId()).isEmpty()) throw new UserException("user not found");

    }
    public void FormQuestionNotFound(QuestionFormRepository repository){
        if(repository.findById(data.questionFormId().getId()).isEmpty()) throw new QuestionException("Form question not found");
    }
  
   
}
