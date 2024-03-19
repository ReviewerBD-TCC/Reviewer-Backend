package com.reviewer.reviewer.dto.forms;

import com.reviewer.reviewer.models.FormQuestion;

import java.time.LocalDate;
import java.time.LocalDateTime;
public record FormQuestionDto(Long formId, String question, LocalDate year) {
    public FormQuestionDto(FormQuestion formQuestion) {
        this(formQuestion.getId(), formQuestion.getQuestion().getQuestion(), formQuestion.getForm().getYear());
    }
}
    

