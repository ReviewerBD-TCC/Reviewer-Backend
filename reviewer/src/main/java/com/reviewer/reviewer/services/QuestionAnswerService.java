package com.reviewer.reviewer.services;
import com.reviewer.reviewer.models.QuestionAnswer;
import com.reviewer.reviewer.models.QuestionForm;

import java.util.ArrayList;
import java.util.List;
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
        var questionAnswer =new QuestionAnswer();
        Validation validation = new Validation(data);
        var user = validation.UserNotFound(userRepository);
        var questionForm = validation.FormQuestionNotFound(questionFormRepository);
        int amountQuestion =0;
        System.out.println(data.answers());
        var questions = data.answers();
        for (String answeString : questions) {
            
            questionAnswer.setAnswer(questions[amountQuestion]);
            questionAnswer.setQuestionForm(eachQuestionForm);
            questionAnswer.setUser(user);
            questionAnswerRepository.save(questionAnswer);
            amountQuestion+=1;
        }
       
    
        return new QuestionAnswerResponseDto(questionAnswer);
    }
    public List<QuestionAnswerResponseDto> findAll(){
        var answers = questionAnswerRepository.findAll();
        List<QuestionAnswerResponseDto> answersDto = new ArrayList<>();
        for (QuestionAnswer questionAnswer : answers) {
            var answerDto = new QuestionAnswerResponseDto(questionAnswer);
            answersDto.add(answerDto);
        }
        return answersDto;
    }
    public QuestionAnswerResponseDto findById(Long id){
        var answer = questionAnswerRepository.findById(id);
        return new QuestionAnswerResponseDto(answer.get());
    }
   public List<QuestionAnswerResponseDto> findByUserId(Long id){
    var answer = questionAnswerRepository.findByUserId(id);
    List<QuestionAnswerResponseDto> answersDto = new ArrayList<>();
    for (QuestionAnswer questionAnswer : answer) {
        var answerDto = new QuestionAnswerResponseDto(questionAnswer);
        answersDto.add(answerDto);
    }
    return answersDto;
   }

}