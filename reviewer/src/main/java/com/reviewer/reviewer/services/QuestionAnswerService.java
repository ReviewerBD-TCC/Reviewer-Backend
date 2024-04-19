package com.reviewer.reviewer.services;
import com.reviewer.reviewer.models.Question;
import com.reviewer.reviewer.models.QuestionAnswer;
import com.reviewer.reviewer.models.QuestionForm;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reviewer.reviewer.dto.questions.QuestionAnswerDto;
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
        List<QuestionResponseDto> questions = new ArrayList<>();
       
        int amountQuestion =0;
        var answers = data.answers();
        var forms = questionForm.stream().toList();
        QuestionAnswerResponseDto questionAnswerResponse = null;
        for (String answeString : answers) {
            var questionAnswer =new QuestionAnswer();
            var question = forms.get(amountQuestion).getQuestion();
            var questionDto = new QuestionResponseDto(question.getId(), question.getQuestionPt(), question.getQuestionEn(),question.getActive());
            questions.add(questionDto);
            System.out.println(forms.get(amountQuestion));
            questionAnswer.setAnswer(answeString);
            questionAnswer.setQuestionForm(forms.get(amountQuestion));
            questionAnswer.setUser(user);
            
            amountQuestion+=1;
            questionAnswerRepository.save(questionAnswer);
            questionAnswerResponse = new QuestionAnswerResponseDto(questionAnswer, questions, answers);
        }
       
        return questionAnswerResponse;
    }
    public List<QuestionAnswerResponseDto> findAll(){
        var answers = questionAnswerRepository.findAll();
        List<QuestionResponseDto> questions = new ArrayList<>();
        int i = 0;
        List<QuestionAnswerResponseDto> answersDto = new ArrayList<>();
       
        for (QuestionAnswer questionAnswer : answers) {
            var question = questionAnswer.getQuestionForm().getQuestion();
            var questionDto = new QuestionResponseDto(question.getId(), question.getQuestionPt(), question.getQuestionEn(),question.getActive());
            questions.add(questionDto);
            String[] answersUser= new String[answers.size()];
            answersUser[i]=questionAnswer.getAnswer();
           
            var answerDto = new QuestionAnswerResponseDto(questionAnswer, questions, answersUser);
            answersDto.add(answerDto);
        }
        return answersDto;
    }
    public QuestionAnswerResponseDto findById(Long id){
        var answer = questionAnswerRepository.findById(id);
        return null;
    }
   public List<QuestionAnswerResponseDto> findByUserId(Long id){
    var answer = questionAnswerRepository.findByUserId(id);
    List<QuestionAnswerResponseDto> answersDto = new ArrayList<>();
    for (QuestionAnswer questionAnswer : answer) {
        // var answerDto = new QuestionAnswerResponseDto(questionAnswer);
        // answersDto.add(answerDto);
    }
    return null;
   }

}