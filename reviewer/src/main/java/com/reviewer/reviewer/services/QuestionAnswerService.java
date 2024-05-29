package com.reviewer.reviewer.services;
import com.reviewer.reviewer.dto.questions.QuestionAnswerResponseDto;
import com.reviewer.reviewer.dto.questions.QuestionResponseDto;
import com.reviewer.reviewer.dto.users.UserResponseDto;
import com.reviewer.reviewer.models.QuestionAnswer;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.reviewer.reviewer.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import com.reviewer.reviewer.dto.questions.QuestionAnswerDto;
import com.reviewer.reviewer.infra.validation.Validation;


@Service
public class QuestionAnswerService {

    @Autowired
    private FormRepository formRepository;
    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionFormRepository questionFormRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public List<QuestionAnswerResponseDto> create(QuestionAnswerDto data, Jwt jwtUser) {
        Validation validation = new Validation(data);

        var questionForms = validation.QuestionFormNotFound(questionFormRepository);
        var user = userService.isInDatabase(new UserResponseDto(jwtUser));
        var forWhichUser = userRepository.findById(data.forWhichUser());
        if(forWhichUser.isEmpty()) throw new NoSuchElementException("To sent your answer its necessary, give the which user id correct!");
        List<QuestionAnswer> answerResponseDtos = new ArrayList<>();

        if (data.questionAnswer() == null || data.questionAnswer().isEmpty()) {
            throw new IllegalArgumentException("The questionAnswer list is empty or null.");
        }
        for (var question : questionForms) {
            System.out.println("Question ID: " + question.getQuestion().getQuestionPt());
        }

        for (var item : data.questionAnswer()) {
            var questionId = item.question();
            var answerText = item.answer().answer();
            var question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new NoSuchElementException("Question not found!"));

            var questionFormPrin = questionFormRepository.findByFormId(data.questionFormId());

            if (questionFormPrin.isEmpty()) {
                throw new NoSuchElementException("QuestionForm not found for question: " + questionId);
            }

            var questionAnswer = new QuestionAnswer(item, user, question, questionFormPrin.get(0), forWhichUser.get());
            questionAnswer.setAnswer(answerText);

            answerResponseDtos.add(questionAnswer);
            questionAnswerRepository.save(questionAnswer);
        }

        return QuestionAnswerResponseDto.fromQuestionAnswerList(answerResponseDtos);
    }

//    public List<QuestionAnswerResponseDto> findAll(){
//        var answersList = questionAnswerRepository.findAll();
//        List<QuestionAnswerResponseDto> answersDto = new ArrayList<>();
//        for (QuestionAnswer questionAnswer : answersList) {
//            var answerDto = new QuestionAnswerResponseDto(questionAnswer);
//            answersDto.add(answerDto);
//        }
//        return answersDto;
//    }

    public List<QuestionAnswerResponseDto> findAllUsersAnsweredTheForm(Long formId, Jwt jwtUser){
        userService.isInDatabase(new UserResponseDto(jwtUser));
        var form = formRepository.findById(formId).orElseThrow(() -> new NoSuchElementException("Form id not found!"));
        var answer = questionAnswerRepository.findAll();
        if (answer.isEmpty()){
            throw new NoSuchElementException("Answer from this user not found!");
        }
        List<QuestionAnswerResponseDto> answersDto = new ArrayList<>();

        for (QuestionAnswer questionAnswer : answer) {
            if(questionAnswer.getQuestionForm().getForm().equals(form)) {
                var question = new QuestionResponseDto(questionAnswer.getQuestion());
                answersDto.add(new QuestionAnswerResponseDto(questionAnswer, question));
            }
            else answersDto.add(new QuestionAnswerResponseDto(questionAnswer, new QuestionResponseDto(questionAnswer.getQuestion())));
        }

        return answersDto;
    }
    public List<QuestionAnswerResponseDto> findAllAnswersByUserId(String userId, Jwt jwtUser){
        userService.isInDatabase(new UserResponseDto(jwtUser));
        var answer = questionAnswerRepository.findAllByUserId(userId);
        if (answer.isEmpty()){
            throw new NoSuchElementException("Answer from this user not found!");
        }
        List<QuestionAnswerResponseDto> answersDto = new ArrayList<>();

        for (QuestionAnswer questionAnswer : answer) {

            var question = new QuestionResponseDto(questionAnswer.getQuestion());
            answersDto.add(new QuestionAnswerResponseDto(questionAnswer, question));

          ;
        }

        return answersDto;
    }
    public List<QuestionAnswerResponseDto> findAllByQuestionId(Long formId, Long questionId, Jwt jwtUser){
        var user = userService.isInDatabase(new UserResponseDto(jwtUser));

        var form = formRepository.findById(formId).orElseThrow(()-> new NoSuchElementException("Form not found!"));
        var answer = questionAnswerRepository.findAllByQuestionId(questionId);
        if(answer.isEmpty()){
            throw new NoSuchElementException("This question is not in form!");
        }
        List<QuestionAnswerResponseDto> answersDto = new ArrayList<>();

        for (QuestionAnswer questionAnswer : answer) {
            if(questionAnswer.getForWhichUser().equals(user) && questionAnswer.getQuestionForm().getForm().equals(form)) {
                var question = new QuestionResponseDto(questionAnswer.getQuestion());
                var answerDto = new QuestionAnswerResponseDto(questionAnswer, question);
                answersDto.add(answerDto);
            }else System.out.println("Não vai man");
        }
        if(answersDto.isEmpty()) throw new NoSuchElementException("User not answered the form ");
        return answersDto;
    }

}