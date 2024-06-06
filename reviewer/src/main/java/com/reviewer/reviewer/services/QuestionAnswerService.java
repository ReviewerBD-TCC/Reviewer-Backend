package com.reviewer.reviewer.services;
import com.reviewer.reviewer.dto.questions.QuestionAnswerResponseDto;
import com.reviewer.reviewer.dto.questions.QuestionResponseDto;
import com.reviewer.reviewer.dto.users.UserResponseDto;
import com.reviewer.reviewer.models.Dashboard;
import com.reviewer.reviewer.models.Form;
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
    private DashboardRepository dashboardRepository;

    @Autowired
    private QuestionFormRepository questionFormRepository;

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private IndicationFormRepository indicationFormRepository;
    @Autowired
    private IndicatedRepository indicatedRepository;

    public List<QuestionAnswerResponseDto> create(QuestionAnswerDto data, Jwt jwtUser) {

        Validation validation = new Validation(data);

        var forWhichUser = userRepository.findById(data.forWhichUser());
        if(forWhichUser.isEmpty()) throw new NoSuchElementException("To sent your answer its necessary, give the which user id correct!");
        var user = userRepository.findById(data.whoAnsweredId()).orElseThrow(()->new NoSuchElementException("User not found!"));
        var alreadyRespondThisFormForWhichUser = questionAnswerRepository.findByUserIdAndForWhichUserId(user.getId(),forWhichUser.get().getId());
        if(alreadyRespondThisFormForWhichUser.size() > 0) throw new IllegalArgumentException("This form already responded, please, wait when you receive a new form to give answer!");
        var indicated = indicatedRepository.findByUserIndicated(user);
        var hasIndication = indicationFormRepository.findByUserIndicationAndIndicatedId(indicated.getId(),forWhichUser.get().getId());

        if(hasIndication.getIsAnswered() == false) {
            hasIndication.setIsAnswered(true);
            indicationFormRepository.save(hasIndication);
        }
        var questionForms = validation.QuestionFormNotFound(questionFormRepository);

        List<QuestionAnswer> answerResponseDtos = new ArrayList<>();

        if (data.questionAnswer() == null || data.questionAnswer().isEmpty()) {
            throw new IllegalArgumentException("The questionAnswer list is empty or null.");
        }
        for (var question : questionForms) {
            System.out.println("Question ID: " + question.getQuestion().getQuestionPt());
        }
        Form form = null;
        for (var item : data.questionAnswer()) {
            var questionId = item.question();
            var answerText = item.answer().answer();
            var question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new NoSuchElementException("Question not found!"));

            var questionFormPrin = questionFormRepository.findByFormId(data.questionFormId());

            if (questionFormPrin.isEmpty()) {
                throw new NoSuchElementException("QuestionForm not found for question: " + questionId);
            }
            form = questionFormPrin.get(0).getForm();
            var questionAnswer = new QuestionAnswer(item, user, question, questionFormPrin.get(0), forWhichUser.get());
            questionAnswer.setAnswer(answerText);

            answerResponseDtos.add(questionAnswer);
            questionAnswerRepository.save(questionAnswer);
        }
        var dashboard = dashboardService.addNewValueFormAnsweredSent(form);
        return QuestionAnswerResponseDto.fromQuestionAnswerList(answerResponseDtos, dashboard);
    }


    public List<QuestionAnswerResponseDto> findAllUsersAnsweredTheForm(Long formId, Jwt jwtUser){

        var form = formRepository.findById(formId).orElseThrow(() -> new NoSuchElementException("Form id not found!"));
        var answer = questionAnswerRepository.findAll();
        if (answer.isEmpty()){
            throw new NoSuchElementException("Answer from this user not found!");
        }
        List<QuestionAnswerResponseDto> answersDto = new ArrayList<>();
        for (QuestionAnswer questionAnswer : answer) {
            Dashboard dashboard = null;
            if(questionAnswer.getQuestionForm().getForm().equals(form)) {
                dashboard = dashboardRepository.findByFormId(questionAnswer.getQuestionForm().getForm().getId());
                var question = new QuestionResponseDto(questionAnswer.getQuestion());
                answersDto.add(new QuestionAnswerResponseDto(questionAnswer, question, dashboard));
            }
            else {
                dashboard = dashboardRepository.findByFormId(questionAnswer.getQuestionForm().getForm().getId());
                answersDto.add(new QuestionAnswerResponseDto(questionAnswer, new QuestionResponseDto(questionAnswer.getQuestion()), dashboard));
            }
        }
        return answersDto;
    }
    public List<QuestionAnswerResponseDto> findAllAnswersByFormAndUserId(Long formId,String userIndicated, Jwt jwtUser){

        var form = formRepository.findById(formId).orElseThrow(() -> new NoSuchElementException("Form id not found!"));
        var dashboard = dashboardRepository.findByFormId(formId);
        var answer = questionAnswerRepository.findAllByForWhichUserId(userIndicated);
        if (answer.isEmpty()){
            throw new NoSuchElementException("Answer from this user not found!");
        }
        List<QuestionAnswerResponseDto> answersDto = new ArrayList<>();

        for (QuestionAnswer questionAnswer : answer) {
            if(questionAnswer.getQuestionForm().getForm().equals(form)) {
                var question = new QuestionResponseDto(questionAnswer.getQuestion());
                answersDto.add(new QuestionAnswerResponseDto(questionAnswer, question, dashboard));
            }
            else answersDto.add(new QuestionAnswerResponseDto(questionAnswer, new QuestionResponseDto(questionAnswer.getQuestion()), dashboard));
        }
        return answersDto;
    }
    public List<QuestionAnswerResponseDto> findAllAnswersByUserId(String userId, Jwt jwtUser){


        var answer = questionAnswerRepository.findAllByUserId(userId);
        if (answer.isEmpty()){
            throw new NoSuchElementException("Answer from this user not found!");
        }
        List<QuestionAnswerResponseDto> answersDto = new ArrayList<>();

        for (QuestionAnswer questionAnswer : answer) {
            var dashboard = dashboardRepository.findByFormId(questionAnswer.getQuestionForm().getForm().getId());
            var question = new QuestionResponseDto(questionAnswer.getQuestion());
            answersDto.add(new QuestionAnswerResponseDto(questionAnswer, question,dashboard));
        }

        return answersDto;
    }
    public List<QuestionAnswerResponseDto> findAllByQuestionId(Long formId, Long questionId, Jwt jwtUser){
        var user = userService.isInDatabase(new UserResponseDto(jwtUser));
        var dashboard = dashboardRepository.findByFormId(formId);
        var form = formRepository.findById(formId).orElseThrow(()-> new NoSuchElementException("Form not found!"));
        var answer = questionAnswerRepository.findAllByQuestionId(questionId);
        if(answer.isEmpty()){
            throw new NoSuchElementException("This question is not in form!");
        }
        List<QuestionAnswerResponseDto> answersDto = new ArrayList<>();

        for (QuestionAnswer questionAnswer : answer) {
            if(questionAnswer.getForWhichUser().equals(user) && questionAnswer.getQuestionForm().getForm().equals(form)) {
                var question = new QuestionResponseDto(questionAnswer.getQuestion());
                var answerDto = new QuestionAnswerResponseDto(questionAnswer, question, dashboard);
                answersDto.add(answerDto);
            }else System.out.println("Não vai man");
        }
        if(answersDto.isEmpty()) throw new NoSuchElementException("User not answered the form ");
        return answersDto;
    }


}