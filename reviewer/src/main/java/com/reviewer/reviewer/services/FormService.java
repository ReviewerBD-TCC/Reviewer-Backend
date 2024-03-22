package com.reviewer.reviewer.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.reviewer.reviewer.models.FormQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.reviewer.reviewer.dto.forms.FormQuestionDto;
import com.reviewer.reviewer.dto.forms.FormsDto;
import com.reviewer.reviewer.dto.forms.QuestionFormListDto;
import com.reviewer.reviewer.dto.forms.QuestionFormResponseDto;
import com.reviewer.reviewer.dto.questions.QuestionDto;
import com.reviewer.reviewer.dto.questions.QuestionsByIdDto;
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
        return new FormsDto(formCreated.getYear(),formCreated.getValidation());

    }
    public FormQuestionDto questionFormCreate(QuestionFormListDto data){
       
        var form = formRepository.findById(data.formId());
        if(form.isEmpty()) throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        List<QuestionsByIdDto> questions = new ArrayList<>();
      

        for(int i =0; i < data.questionsId().size(); i++){
            var questionById = questionRepository.findById(data.questionsId().get(i).longValue());
            if(questionById.isEmpty()) throw new ResponseStatusException(HttpStatusCode.valueOf(404));
            var formQuestion = new FormQuestion(form.get(), questionById.get());
            questionFormRepository.save(formQuestion);
            var questionDto = new QuestionsByIdDto(questionById.get());
            questions.add(questionDto);
        }
        return new FormQuestionDto(form.get().getId(),questions, form.get().getYear());

    }
    public List<QuestionFormResponseDto> listFormQuestion(Long formId) {
		var formQuestions = questionFormRepository.findAllByFormId(formId);
		List<QuestionFormResponseDto>questionFormResponseDtos = new ArrayList<>();

        if (formQuestions.isEmpty()) {
            throw new NoSuchElementException("Id form: " + formId + " not found");
        }
        for (FormQuestion formQuestion : formQuestions) {
			var entity = new QuestionFormResponseDto(formQuestion);
            questionFormResponseDtos.add(entity);
		}
		return questionFormResponseDtos;
		}
    
}
