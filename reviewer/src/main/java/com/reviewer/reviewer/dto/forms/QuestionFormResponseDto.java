package com.reviewer.reviewer.dto.forms;


import java.time.LocalDate;
import java.util.List;

import com.reviewer.reviewer.dto.questions.QuestionResponseDto;
import com.reviewer.reviewer.models.QuestionForm;


public record QuestionFormResponseDto(Long id, String title, List<QuestionResponseDto> questions, LocalDate year) {
    
    public QuestionFormResponseDto(QuestionForm data, List<QuestionResponseDto> questions){
        this(data.getForm().getId(), data.getForm().getTitle(), questions,data.getForm().getYear());
    }
}