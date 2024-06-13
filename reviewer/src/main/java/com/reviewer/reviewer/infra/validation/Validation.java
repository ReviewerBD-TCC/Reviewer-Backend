package com.reviewer.reviewer.infra.validation;
import java.util.List;

import com.reviewer.reviewer.dto.questions.QuestionAnswerDto;
import com.reviewer.reviewer.models.QuestionForm;
import com.reviewer.reviewer.models.User;
import com.reviewer.reviewer.repositories.QuestionFormRepository;
import com.reviewer.reviewer.repositories.UserRepository;

public record Validation (QuestionAnswerDto data){

//    public User UserNotFound(UserRepository repository){
//        var user = repository.findById(data.userId());
//        return user.get();
//    }
    public List<QuestionForm> QuestionFormNotFound(QuestionFormRepository repository){
        var questionForms = repository.findAllByFormId(data.questionFormId());
        return questionForms;
    }
  
   
}
