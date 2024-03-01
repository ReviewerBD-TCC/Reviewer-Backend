package com.reviewer.reviewer.services;

import java.util.ArrayList;
import java.util.List;

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

    public QuestionDto create(QuestionDto data){
        var question = new Question(data);
        repository.save(question);
        return data;
    }
    public QuestionDto update(Long id, QuestionDto data){
        var question = repository.findById(id);
        if(question.isEmpty()) throw new QuestionException("erro ao encontrar question");
        var entityQuestion = question.get();
        entityQuestion.setQuestion(data.question());
        entityQuestion.setActive(data.active());
        repository.save(entityQuestion);

        return data;

    }
    public QuestionDto findById(Long id){
        var question = repository.findById(id);
        if(question.isEmpty()) throw new QuestionException("erro ao encontrar question");
        var questionConverted = new QuestionDto(question.get().getQuestion(), question.get().isActive());

        return questionConverted;

    }
    public List<QuestionDto> findAll(){
        var questions = repository.findAll();
        List<QuestionDto> questionsDto = new ArrayList<>();
        for (Question question : questions) {
            var questionConverted = new QuestionDto(question.getQuestion(), question.isActive());
            questionsDto.add(questionConverted);
        }
        return questionsDto;
    }

}
