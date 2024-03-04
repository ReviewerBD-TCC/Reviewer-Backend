package com.reviewer.reviewer.services;

import com.reviewer.reviewer.models.QuestionAnswer;
import com.reviewer.reviewer.models.QuestionFormAnswer;
import com.reviewer.reviewer.models.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reviewer.reviewer.dto.questions.QuestionAnswerDto;
import com.reviewer.reviewer.dto.questions.QuestionDto;
import com.reviewer.reviewer.infra.exceptions.QuestionException;
import com.reviewer.reviewer.infra.exceptions.UserException;
import com.reviewer.reviewer.infra.validation.Validation;
import com.reviewer.reviewer.repositories.QuestionAnswerRepository;
import com.reviewer.reviewer.repositories.QuestionFormAnswerRepository;
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
    
    @Autowired
    private QuestionFormAnswerRepository questionFormAnswerRepository;

    public QuestionAnswerDto create(QuestionAnswerDto data) {
  
        Validation validation = new Validation(data);
        var user = validation.UserNotFound(userRepository);
        var questionForm = validation.FormQuestionNotFound(questionFormRepository);

     
        var questionAnswer = new QuestionAnswer(null,user,null,data.answer());
        questionAnswerRepository.save(questionAnswer);

      
        var questionFormAnswer = new QuestionFormAnswer(null, questionForm, questionAnswer);
        questionFormAnswerRepository.save(questionFormAnswer);

        return new QuestionAnswerDto(questionAnswer.getId(),user.getId(), user.getName(), questionForm.getId(), questionForm.getQuestion().getQuestion(), data.answer());
    }
    // public QuestionAnswerDto findById(Long id){
    //     var entityQuestionAnswer = questionAnswerRepository.findById(id);
    //     if(entityQuestionAnswer.isEmpty()) throw new RuntimeException();
    //     return entityQuestionAnswer.stream().map(data -> 
    //     new QuestionAnswerDto(id, data.get().getUser().getId(),
    //      data.get().getUser().getName(), 
    //      data.getQuestionFormAnswers()., null, null));
       
        

        
    // }

}