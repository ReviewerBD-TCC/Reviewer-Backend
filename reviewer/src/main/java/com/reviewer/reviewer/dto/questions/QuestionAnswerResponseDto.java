package com.reviewer.reviewer.dto.questions;

import java.util.List;

import com.reviewer.reviewer.dto.forms.FormQuestionDto;
import com.reviewer.reviewer.models.QuestionAnswer;


public record QuestionAnswerResponseDto(Long id, Long userId, String name, String question, String answer) {
    public QuestionAnswerResponseDto(QuestionAnswer data){
        this(data.getId(), data.getUser().getId(), data.getUser().getName(), data.getQuestionForm().getQuestion().getQuestion(), data.getAnswer());
    }
    
}
