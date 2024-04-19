package com.reviewer.reviewer.dto.questions;
import java.util.List;

import com.reviewer.reviewer.models.QuestionAnswer;



public record QuestionAnswerResponseDto(Long id, Long userId, String name, List<QuestionResponseDto> questions, String [] answers) {
    public QuestionAnswerResponseDto(QuestionAnswer data, List<QuestionResponseDto> questions, String [] answers){
        this(data.getId(), data.getUser().getId(), data.getUser().getName(), questions, answers);
    }

    
    
}
