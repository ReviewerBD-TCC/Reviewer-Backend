package com.reviewer.reviewer.services;

import com.reviewer.reviewer.models.QuestionAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reviewer.reviewer.dto.questions.QuestionAnswerDto;
import com.reviewer.reviewer.dto.questions.QuestionDto;
import com.reviewer.reviewer.infra.exceptions.QuestionException;
import com.reviewer.reviewer.infra.exceptions.UserException;
import com.reviewer.reviewer.infra.validation.Validation;
import com.reviewer.reviewer.repositories.QuestionAnswerRepository;
import com.reviewer.reviewer.repositories.QuestionFormRepository;
import com.reviewer.reviewer.repositories.UserRepository;

@Service
public class QuestionAnswerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    @Autowired
    private QuestionFormRepository questionFormRepository;

    private Validation validation;

    public QuestionAnswerDto create(QuestionAnswerDto data ){
        
        validation = new Validation(data);
        validation.UserNotFound(userRepository);
        validation.FormQuestionNotFound(questionFormRepository);
        var answerUser = new QuestionAnswer(data);
        questionAnswerRepository.save(answerUser);
        return new QuestionAnswerDto(answerUser);
    }

}
