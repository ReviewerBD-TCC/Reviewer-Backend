package com.reviewer.reviewer.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.swing.text.StyledEditorKit.BoldAction;

import com.reviewer.reviewer.dto.forms.*;
import com.reviewer.reviewer.dto.questions.QuestionResponseDto;
import com.reviewer.reviewer.models.*;
import com.reviewer.reviewer.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private QuestionFormRepository questionFormRepository;

    @Autowired
    private IndicationFormRepository indicationFormRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    public QuestionFormListDto create(QuestionFormCreatedDto data) {

        var form = new Form();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var today = LocalDate.now();
        LocalDate year = data.year();
        var date = LocalDate.of(year.getYear(), today.getMonthValue(), today.getDayOfMonth());
        String format = date.format(formatTime);
        LocalDate formattedDate = LocalDate.parse(format, formatTime);
        form.setYear(formattedDate);
        form.setTitle(data.title());
        form.setValidation(form.getYear().plusYears(1));
        formRepository.save(form);
        List<QuestionResponseDto> questions = new ArrayList<>();

        List<IndicationForm> indication = indicationFormRepository.findByUserIndicationId(data.indication());

        var listIndication = new IndicationFormResponseDto(indication);

        for (int i = 0; i < data.questions().size(); i++) {
            long eachQuestionId = data.questions().get(i).question();
            var before = data.questions().get(i).question();
            for (int j = 0; j < i; j++) {
                var next = data.questions().get(j).question();
                if (next.equals(before)) {
                    throw new IllegalArgumentException("Question id " + eachQuestionId + " is duplicated!");
                }
            }
            if (eachQuestionId <= 0) {
                throw new NoSuchElementException("Question id " + eachQuestionId + " not exists!");
            }
            var questionById = questionRepository.findById(data.questions().get(i).question());
            if (questionById.isEmpty()) {
                throw new ResponseStatusException(HttpStatusCode.valueOf(404));
            }
            var formQuestion = new QuestionForm(form, questionById.get());
            questionFormRepository.save(formQuestion);
            var questionDto = new QuestionResponseDto(questionById.get());
            questions.add(questionDto);
        }
        return new QuestionFormListDto(form.getId(), Collections.singletonList(listIndication), form.getTitle(), form.getYear(), questions);

    }

    public List<QuestionFormResponseDto> listFormQuestion(Long formId) {
        var formQuestions = questionFormRepository.findAllByFormId(formId);

        if (formQuestions.isEmpty()) {
            throw new NoSuchElementException("Form ID " + formId + " not found.");
        }

        // Para evitar duplicatas, use um Set para manter registros únicos
        Set<Long> uniqueFormIds = new HashSet<>();
        List<QuestionFormResponseDto> questionFormResponseDtos = new ArrayList<>();

        // Para cada `QuestionForm`, cria um `QuestionFormResponseDto`
        for (var formQuestion : formQuestions) {
            if (!uniqueFormIds.contains(formQuestion.getForm().getId())) {
                uniqueFormIds.add(formQuestion.getForm().getId());
                var responseDto = new QuestionFormResponseDto(formQuestion);
                questionFormResponseDtos.add(responseDto);
            }
        }

        return questionFormResponseDtos; // Retorna a lista de DTOs
    }

    public List<QuestionFormResponseDto> findAll() {
        var forms = questionFormRepository.findAll();
        List<QuestionFormResponseDto> formsDto = new ArrayList<>();

        for (QuestionForm form : forms) {

            System.out.println(form.getIndication());

            boolean exists = formsDto.stream().anyMatch(dto -> dto.id().equals(form.getForm().getId()));
            if (!exists) {
                QuestionFormResponseDto questionFormDto = new QuestionFormResponseDto(form);
                formsDto.add(questionFormDto);
            }
        }
        return formsDto;
    }

    public <T> Object updateForm(Long id, FormUpdateDto data) {
        var form = formRepository.findById(id);
        var questionForms = questionFormRepository.findAllByFormId(id);
        if (form.isEmpty()) {
            throw new NoSuchElementException("Form not found!");
        }
        int index = 0;
        for (QuestionForm questionForm : questionForms) {
            boolean throwException = false;
            if (questionForm.getForm().equals(form.get())) {
                var question = questionRepository.findById(data.newQuestions().get(index).questionId());
                if (questionForm.getQuestion().getId().equals(question.get().getId())) {
                    var newQuestion = questionRepository.findById(data.newQuestions().get(index).newQuestionId());
                    questionForm.setQuestion(newQuestion.get());
                    questionFormRepository.save(questionForm);
                    if(index < data.newQuestions().size() && data.newQuestions().size()>1){
                        index+=1;
                    }
                } else {
                    throwException = true;
                    if(throwException == true && questionForm.getQuestion().equals(question.get())){
                        throw new NoSuchElementException("Question id " + question.get().getId()
                                + " is not relationated with form id " + id + "!");
                    }
                }
            }
        }
        if (data.title() != null) {
            form.get().setTitle(data.title());
            formRepository.save(form.get());
        }
        return null;
    }

    public void deleteForm(Long id) {
        var form = formRepository.findById(id);
        var questionForm = questionFormRepository.findAllByFormId(id);
        var questionsAnswers = questionAnswerRepository.findAll();

        if (form.isEmpty()) {
            throw new NoSuchElementException("Form not found!");
        } else {
            formRepository.delete(form.get());
            questionForm.forEach(eachQuestionForm -> {
                if (eachQuestionForm.getForm().equals(form.get())) {
                    questionFormRepository.delete(eachQuestionForm);
                }
            });
            questionsAnswers.forEach(each -> {
                if (each.getQuestionForm().getForm().equals(form.get())) {
                    questionAnswerRepository.delete(each);
                }
            });
        }
    }
}
