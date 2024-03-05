package com.reviewer.reviewer.services;

import java.util.ArrayList;
import java.util.List;

import com.reviewer.reviewer.dto.questions.QuestionDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reviewer.reviewer.dto.questions.QuestionDto;
import com.reviewer.reviewer.infra.exceptions.QuestionException;
import com.reviewer.reviewer.models.Question;
import com.reviewer.reviewer.repositories.QuestionRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repository;

    public QuestionDetailsDto create(QuestionDto data){
        var question = new Question(data);

        repository.save(question);

        return new QuestionDetailsDto(question);
    }
    public QuestionDetailsDto update(Long id, QuestionDto data){
        var question = repository.findById(id);
        if(question.isEmpty()) throw new QuestionException("erro ao encontrar question");
        var entityQuestion = question.get();
        entityQuestion.setQuestion(data.question());
        entityQuestion.setActive(data.active());
        repository.save(entityQuestion);

        return new QuestionDetailsDto(entityQuestion);

    }
    public QuestionDetailsDto findById(Long id){
        var question = repository.findById(id);
        if(question.isEmpty()) throw new QuestionException("erro ao encontrar question");

        return new QuestionDetailsDto(question.get());

    }
    public List<QuestionDetailsDto> findAll(){
        var questions = repository.findAll();
        List<QuestionDetailsDto> questionsDto = new ArrayList<>();
        for (Question question : questions) {
            var questionConverted = new QuestionDetailsDto(question);
            questionsDto.add(questionConverted);
        }
        return questionsDto;
    }

}
