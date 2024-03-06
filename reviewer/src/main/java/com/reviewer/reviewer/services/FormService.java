package com.reviewer.reviewer.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.reviewer.reviewer.models.FormQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.reviewer.reviewer.dto.forms.FormQuestionDto;
import com.reviewer.reviewer.dto.forms.FormsDto;
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

    public FormsDto create(){
         var form = new Form();
        form.setYear(LocalDate.now());
        form.setValidation(LocalDate.now().plusYears(1));
        var formCreated = formRepository.save(form);
        System.out.println(formCreated);
        return new FormsDto(formCreated.getId(),formCreated.getYear(),formCreated.getValidation());

    }
    public FormQuestionDto createFormQuestion(Long formId, Long questionId){

        var form = formRepository.findById(formId);
        if(form.isEmpty()) throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        var question = questionRepository.findById(questionId);
        if(question.isEmpty()) throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        var formQuestion = new FormQuestion(null, form.get(), question.get());
        questionFormRepository.save(formQuestion);

        return new FormQuestionDto( form.get().getId(), question.get().getQuestion(), form.get().getYear());

    }
    public List<FormQuestionDto> listFormQuestion(Long formId) {
		var formQuestions = questionFormRepository.findAllByFormId(formId);
		List<FormQuestionDto>formQuestionDtos = new ArrayList<>();
        if(formQuestions.isEmpty()) throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        for (FormQuestion formQuestion : formQuestions) {
			var entity = new FormQuestionDto(formQuestion.getForm().getId(), formQuestion.getQuestion().getQuestion(), formQuestion.getForm().getYear());
            formQuestionDtos.add(entity);
		}
		return formQuestionDtos;
		}
    
}
