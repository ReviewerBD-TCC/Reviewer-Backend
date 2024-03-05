package com.reviewer.reviewer.dto.questions;

import java.util.List;

import com.reviewer.reviewer.dto.forms.FormQuestionDto;


public record QuestionAnswerResponseDto(Long id, Long userId, String name, List<FormQuestionDto> FormQuestion, String answer) {
    
    
}
