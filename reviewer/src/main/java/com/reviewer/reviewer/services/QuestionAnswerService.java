package com.reviewer.reviewer.services;
import com.reviewer.reviewer.models.Question;
import com.reviewer.reviewer.models.QuestionAnswer;
import com.reviewer.reviewer.models.QuestionForm;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reviewer.reviewer.dto.questions.QuestionAnswerDto;
import com.reviewer.reviewer.dto.questions.QuestionAnswerFindAllDto;
import com.reviewer.reviewer.dto.questions.QuestionAnswerResponseDto;
import com.reviewer.reviewer.dto.questions.QuestionDto;
import com.reviewer.reviewer.dto.questions.QuestionResponseDto;
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
      
        int amountQuestion =0;
        var answersList = data.answers();
        List<QuestionResponseDto> questions = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        var forms = questionForm.stream().toList();
        QuestionAnswerResponseDto questionAnswerResponse = null;
        for (String answer : answersList) {
            var questionAnswer =new QuestionAnswer();
            var question = new QuestionResponseDto(forms.get(amountQuestion).getQuestion());
            answers.add(answer);
            questions.add(question);
            questionAnswer.setAnswer(answer);
            questionAnswer.setQuestionForm(forms.get(amountQuestion));
            questionAnswer.setUser(user);
            
            amountQuestion+=1;
            questionAnswerRepository.save(questionAnswer);
            questionAnswerResponse = new QuestionAnswerResponseDto(questionAnswer,questions, answers);
        }
       
        return questionAnswerResponse;
    }
    public List<QuestionAnswerFindAllDto> findAll(){
        var answersList = questionAnswerRepository.findAll();
        List<QuestionAnswerFindAllDto> answersDto = new ArrayList<>();
        for (QuestionAnswer questionAnswer : answersList) {
        
            var answerDto = new QuestionAnswerFindAllDto(questionAnswer);
            answersDto.add(answerDto);
        }
        return answersDto;
    }
    public QuestionAnswerFindAllDto findById(Long id){
        var answer = questionAnswerRepository.findById(id);
        return new QuestionAnswerFindAllDto(answer.get());
    }
   public List<QuestionAnswerFindAllDto> findByUserId(Long id){
    var answer = questionAnswerRepository.findByUserId(id);
    List<QuestionAnswerFindAllDto> answersDto = new ArrayList<>();
    for (QuestionAnswer questionAnswer : answer) {
        var answerDto = new QuestionAnswerFindAllDto(questionAnswer);
        answersDto.add(answerDto);
    }
    return answersDto;
   }

}