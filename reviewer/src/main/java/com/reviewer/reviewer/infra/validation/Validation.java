package com.reviewer.reviewer.infra.validation;

import com.reviewer.reviewer.dto.questions.QuestionAnswerDto;
import com.reviewer.reviewer.infra.exceptions.QuestionException;
import com.reviewer.reviewer.infra.exceptions.UserException;
import com.reviewer.reviewer.models.QuestionForm;
import com.reviewer.reviewer.models.User;

import com.reviewer.reviewer.repositories.QuestionFormRepository;
import com.reviewer.reviewer.repositories.UserRepository;

public record Validation (QuestionAnswerDto data){


    public User UserNotFound(UserRepository repository){
        var user = repository.findById(data.userId());
        if(user.isEmpty()) throw new UserException("user not found");
        return user.get();
    }
    public QuestionForm FormQuestionNotFound(QuestionFormRepository repository){
        var questionForm = repository.findById(data.questionFormId());
        if(questionForm.isEmpty()) throw new QuestionException("Form question not found");
        return questionForm.get();
    }
  
   
}
