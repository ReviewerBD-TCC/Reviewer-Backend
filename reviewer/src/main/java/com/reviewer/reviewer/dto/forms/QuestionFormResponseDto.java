package com.reviewer.reviewer.dto.forms;


import java.time.LocalDate;

import com.reviewer.reviewer.models.Question;
import com.reviewer.reviewer.models.QuestionForm;


public record QuestionFormResponseDto(Long id, String title, String questionPt,String questionEn, LocalDate year) {
    
    public QuestionFormResponseDto(QuestionForm data){
        this(data.getForm().getId(), data.getForm().getTitle(),data.getQuestion().getQuestionPt(),data.getQuestion().getQuestionEn(), data.getForm().getYear());
    }
}