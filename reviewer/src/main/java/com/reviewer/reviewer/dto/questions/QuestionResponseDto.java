package com.reviewer.reviewer.dto.questions;

import com.reviewer.reviewer.models.Question;

public record QuestionResponseDto(
        Long id,
        String questionPt,
        String questionEn,
        Boolean active
) {

    public QuestionResponseDto(Question question) {
        this(question.getId(), question.getQuestionPt(),question.getQuestionEn(), question.getActive());
    }
}
