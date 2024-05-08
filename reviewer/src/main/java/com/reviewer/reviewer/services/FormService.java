package com.reviewer.reviewer.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.reviewer.reviewer.dto.questions.QuestionResponseDto;
import com.reviewer.reviewer.models.QuestionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.reviewer.reviewer.dto.forms.QuestionFormCreatedDto;
import com.reviewer.reviewer.dto.forms.QuestionFormListDto;
import com.reviewer.reviewer.dto.forms.QuestionFormResponseDto;
import com.reviewer.reviewer.models.Form;
import com.reviewer.reviewer.repositories.FormRepository;
import com.reviewer.reviewer.repositories.QuestionFormRepository;
import com.reviewer.reviewer.repositories.QuestionRepository;

@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private QuestionFormRepository questionFormRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public QuestionFormListDto create(QuestionFormCreatedDto data) {

        var form = new Form();

        System.out.println(data.year());

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

        for (int i = 0; i < data.questions().size(); i++) {

            var questionById = questionRepository.findById(data.questions().get(i).question());

            if (questionById.isEmpty()) {
                throw new ResponseStatusException(HttpStatusCode.valueOf(404));
            }

            var formQuestion = new QuestionForm(form, questionById.get());
            questionFormRepository.save(formQuestion);
            var questionDto = new QuestionResponseDto(questionById.get());
            questions.add(questionDto);
        }
        return new QuestionFormListDto(form.getId(), form.getTitle(), form.getYear(), questions);

    }

    public List<QuestionFormResponseDto> listFormQuestion(Long formId) {
        var formQuestions = questionFormRepository.findAllByFormId(formId);
        List<QuestionFormResponseDto> questionFormResponseDtos = new ArrayList<>();

        if (formQuestions.isEmpty()) {
            throw new NoSuchElementException("Id form: " + formId + " not found");
        }
        for (QuestionForm formQuestion : formQuestions) {
            var entity = new QuestionFormResponseDto(formQuestion);
            questionFormResponseDtos.add(entity);
        }
        return questionFormResponseDtos;
    }
    public List<QuestionFormResponseDto> findAll(){
        var forms = questionFormRepository.findAll();
        List<QuestionFormResponseDto> formsDto = new ArrayList<>();
        List<QuestionResponseDto> questions = new ArrayList<>();
        forms.forEach(each->{
            var question = new QuestionResponseDto(each.getQuestion());
            questions.add(question);
            var questionForm = new QuestionFormResponseDto(each);
            formsDto.add(questionForm);
        });
        return formsDto;
      
    }
    
}
