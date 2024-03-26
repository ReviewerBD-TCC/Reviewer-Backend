package com.reviewer.reviewer.dto.questions;

import com.reviewer.reviewer.models.Question;

public record QuestionsByIdDto( String question, Boolean active) {
    public QuestionsByIdDto(Question data){
        this(data.getQuestionPt(), data.getActive());
    }
}
