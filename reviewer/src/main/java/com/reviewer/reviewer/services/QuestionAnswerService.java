package com.reviewer.reviewer.services;

import com.reviewer.reviewer.models.QuestionAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reviewer.reviewer.dto.questions.QuestionAnswerDto;
import com.reviewer.reviewer.dto.questions.QuestionAnswerResponseDto;
import com.reviewer.reviewer.infra.validation.Validation;
import com.reviewer.reviewer.repositories.QuestionAnswerRepository;
import com.reviewer.reviewer.repositories.QuestionFormRepository;
import com.reviewer.reviewer.repositories.UserRepository;


@Service
public class QuestionAnswerService {


    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionFormRepository questionFormRepository;
    


    public QuestionAnswerResponseDto create(QuestionAnswerDto data) {
  
        Validation validation = new Validation(data);
        var user = validation.UserNotFound(userRepository);
        var questionForm = validation.FormQuestionNotFound(questionFormRepository);
     
        var questionAnswer = new QuestionAnswer( user,  questionForm, data.answer());
        questionAnswerRepository.save(questionAnswer);
    
        return new QuestionAnswerResponseDto(questionAnswer);
    }
   

}