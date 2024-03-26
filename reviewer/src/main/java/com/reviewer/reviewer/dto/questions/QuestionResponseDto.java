package com.reviewer.reviewer.dto.questions;

import com.reviewer.reviewer.models.Question;

public record QuestionResponseDto(
        Long id,
        String question,
        Boolean active
) {

    public QuestionResponseDto(Question question) {
        this(question.getId(), question.getQuestion(), question.getActive());
    }
}
