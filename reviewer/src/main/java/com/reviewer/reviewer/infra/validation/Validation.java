package com.reviewer.reviewer.infra.validation;

import com.reviewer.reviewer.dto.questions.QuestionAnswerDto;
import com.reviewer.reviewer.models.FormQuestion;
import com.reviewer.reviewer.models.User;

import com.reviewer.reviewer.repositories.QuestionFormRepository;
import com.reviewer.reviewer.repositories.UserRepository;

public record Validation (QuestionAnswerDto data){


    public User UserNotFound(UserRepository repository){
        var user = repository.findById(data.userId());
        return user.get();
    }
    public FormQuestion FormQuestionNotFound(QuestionFormRepository repository){
        var formQuestion = repository.findById(data.questionFormId());
        return formQuestion.get();
    }
  
   
}
