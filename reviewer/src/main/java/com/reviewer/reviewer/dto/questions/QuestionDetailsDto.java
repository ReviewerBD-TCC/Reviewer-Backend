package com.reviewer.reviewer.dto.questions;

import com.reviewer.reviewer.models.Question;

public record QuestionDetailsDto(
        Long id,
        String question,
        Boolean active
) {

    public QuestionDetailsDto(Question question) {
        this(question.getId(), question.getQuestion(), question.getActive());
    }
}
