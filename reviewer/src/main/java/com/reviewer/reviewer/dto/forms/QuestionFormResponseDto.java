package com.reviewer.reviewer.dto.forms;


import java.time.LocalDate;

import com.reviewer.reviewer.models.QuestionForm;


public record QuestionFormResponseDto(Long id, String questionPt, String quentionEn, LocalDate year) {
    
    public QuestionFormResponseDto(QuestionForm data){
        this(data.getForm().getId(), data.getQuestion().getQuestionPt(), data.getQuestion().getQuestionEn(),data.getForm().getYear());
    }
}