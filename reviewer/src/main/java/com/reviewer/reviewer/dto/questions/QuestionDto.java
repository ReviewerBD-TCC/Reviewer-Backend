package com.reviewer.reviewer.dto.questions;

import com.reviewer.reviewer.models.Question;

public record QuestionDto(String question, Boolean active) {
    public QuestionDto(Question question) {
        this(question.getQuestion(), question.getActive());
    }
}
