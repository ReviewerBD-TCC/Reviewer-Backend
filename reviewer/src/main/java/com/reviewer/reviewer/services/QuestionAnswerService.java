package com.reviewer.reviewer.services;

import com.reviewer.reviewer.dto.questions.QuestionAnswerResponseDto;
import com.reviewer.reviewer.dto.questions.QuestionResponseDto;
import com.reviewer.reviewer.models.QuestionAnswer;
import com.reviewer.reviewer.models.QuestionForm;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.reviewer.reviewer.repositories.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reviewer.reviewer.dto.questions.QuestionAnswerDto;
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

    @Autowired
    private QuestionRepository questionRepository;

    // public List<QuestionAnswerResponseDto> create(QuestionAnswerDto data) {
    //     Validation validation = new Validation(data);
    //     var user = validation.UserNotFound(userRepository);
    //     List<QuestionAnswer> answerResponseDtos = new ArrayList<>();

    //     if (data.questionAnswer() == null || data.questionAnswer().isEmpty()) {
    //         throw new IllegalArgumentException("The questionAnswer list is empty or null.");
    //     }

    //     for (var item : data.questionAnswer()) {
    //         var questionId = item.question();
    //         var answerText = item.answer().answer();

    //         var question = questionRepository.findById(questionId)
    //                 .orElseThrow(() -> new NoSuchElementException("Question not found!"));

    //         // Obter o questionForm correspondente ao questionId
    //         var questionForm = question.getQuestionForms();

    //         // Verificar se há pelo menos um questionForm associado à pergunta
    //         if (questionForm.isEmpty()) {
    //             throw new NoSuchElementException("QuestionForm not found for question: " + questionId);
    //         }

    //         // Iterar sobre todos os questionForms associados à pergunta
    //         for (var form : questionForm) {
    //             System.out.println("QuestionForm: " + form.getId());
    //             var questionAnswer = new QuestionAnswer(item, user, question, form);
    //             questionAnswer.setAnswer(answerText);

    //             answerResponseDtos.add(questionAnswer);
    //             questionAnswerRepository.save(questionAnswer);
    //         }
    //     }

    //     return QuestionAnswerResponseDto.fromQuestionAnswerList(answerResponseDtos);
    // }

    public List<QuestionAnswerResponseDto> create(QuestionAnswerDto data) {
        Validation validation = new Validation(data);
        var questionForms = validation.QuestionFormNotFound(questionFormRepository);
        var user = validation.UserNotFound(userRepository);
        List<QuestionAnswer> answerResponseDtos = new ArrayList<>();
        int i;
        if (data.questionAnswer() == null || data.questionAnswer().isEmpty()) {
            throw new IllegalArgumentException("The questionAnswer list is empty or null.");
        }

        // Itera sobre as perguntas do questionForm e imprime suas IDs
        for (var question : questionForms) {
            System.out.println("Question ID: " + question.getQuestion().getQuestionPt());
        }

        for (var item : data.questionAnswer()) {
            var questionId = item.question();
            var answerText = item.answer().answer();
            var question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new NoSuchElementException("Question not found!"));

            // Obter o questionForm correspondente ao questionId
            var questionForm = question.getQuestionForms();

            // Verificar se há pelo menos um questionForm associado à pergunta
            if (questionForm.isEmpty()) {
                throw new NoSuchElementException("QuestionForm not found for question: " + questionId);
            }

            System.out.println("QuestionForm: " + questionForm.get(0).getForm().getId());
            System.out.println("Question do banco: " + question.getId());

            var questionAnswer = new QuestionAnswer(item, user, question, questionForm.get(0));
            questionAnswer.setAnswer(answerText);

            answerResponseDtos.add(questionAnswer);
            questionAnswerRepository.save(questionAnswer);
        }

        return QuestionAnswerResponseDto.fromQuestionAnswerList(answerResponseDtos);
    }

    // public List<QuestionAnswerResponseDto> findAll() {
    //     var answersList = questionAnswerRepository.findAll();
    //     List<QuestionAnswerResponseDto> answersDto = new ArrayList<>();
    //     for (QuestionAnswer questionAnswer : answersList) {
    //         var answerDto = new QuestionAnswerResponseDto(questionAnswer);
    //         answersDto.add(answerDto);
    //     }
    //     return answersDto;
    // }

    public List<QuestionAnswerResponseDto> findByUserId(Long id) {

        var answer = questionAnswerRepository.findByUserId(id);

        List<QuestionAnswerResponseDto> answersDto = new ArrayList<>();

        for (QuestionAnswer questionAnswer : answer) {
            var question = new QuestionResponseDto(questionAnswer.getQuestion());
            var answerDto = new QuestionAnswerResponseDto(questionAnswer, question);
            answersDto.add(answerDto);
        }

        return answersDto;
    }

}