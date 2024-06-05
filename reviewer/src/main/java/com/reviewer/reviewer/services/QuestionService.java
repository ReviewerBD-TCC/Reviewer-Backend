package com.reviewer.reviewer.services;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import com.reviewer.reviewer.dto.questions.QuestionResponseDto;
import com.reviewer.reviewer.dto.users.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.reviewer.reviewer.dto.questions.QuestionActiveDto;
import com.reviewer.reviewer.dto.questions.QuestionDto;
import com.reviewer.reviewer.models.Question;
import com.reviewer.reviewer.repositories.QuestionRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repository;
    @Autowired
    private UserService userService;

    public QuestionResponseDto create(QuestionDto data){

        var question = new Question(data);

        repository.save(question);

        return new QuestionResponseDto(question);
    }

    public QuestionResponseDto update(Long id, QuestionDto data, Jwt jwtUser){
        userService.isInDatabase(new UserResponseDto(jwtUser));
        var question = repository.findById(id);
        if(question.isEmpty()) throw new NoSuchElementException("Id question: " + id + " not found");
        var entityQuestion = question.get();
        entityQuestion.setQuestionPt(data.questionPt());
        entityQuestion.setQuestionEn(data.questionEn());
        entityQuestion.setActive(data.active());
        repository.save(entityQuestion);

        return new QuestionResponseDto(question.get().getId(),  question.get().getQuestionPt(), question.get().getQuestionEn(), question.get().getActive());

    }
    public QuestionResponseDto partialUpdate(Long id, QuestionActiveDto data,Jwt jwtUser){
        userService.isInDatabase(new UserResponseDto(jwtUser));
        var question = repository.findById(id);
        if(question.isEmpty()) throw new NoSuchElementException("Id question: " + id + " not found");
        question.get().setActive(data.active());
        repository.save(question.get());
        return new QuestionResponseDto(question.get());
    }
    
    public QuestionResponseDto findById(Long id, Jwt jwtUser){
        userService.isInDatabase(new UserResponseDto(jwtUser));
        var question = repository.findById(id);

        if (question.isEmpty()) {
            throw new NoSuchElementException("Id question: " + id + " not found");
        }

        return new QuestionResponseDto(question.get());

    }
    public List<QuestionResponseDto> findAll(){
        var questions = repository.findAll();
        List<QuestionResponseDto> questionsDto = new ArrayList<>();
        for (Question question : questions) {
            var questionConverted = new QuestionResponseDto(question);
            questionsDto.add(questionConverted);
        }
        return questionsDto;
    }

}
