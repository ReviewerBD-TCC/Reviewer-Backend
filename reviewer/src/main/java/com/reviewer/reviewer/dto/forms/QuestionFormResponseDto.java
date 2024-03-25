package com.reviewer.reviewer.dto.forms;

import java.time.LocalDate;

import com.reviewer.reviewer.models.FormQuestion;

public record QuestionFormResponseDto(Long id, String questions, LocalDate year) {
    
    public QuestionFormResponseDto(FormQuestion data){
        this(data.getForm().getId(), data.getQuestion().getQuestion(), data.getForm().getYear());
    }
}
