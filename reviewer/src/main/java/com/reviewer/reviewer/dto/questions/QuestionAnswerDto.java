package com.reviewer.reviewer.dto.questions;

import com.reviewer.reviewer.models.QuestionAnswer;
import com.reviewer.reviewer.models.QuestionForm;
import com.reviewer.reviewer.models.User;

public record QuestionAnswerDto (Long id, User userId, QuestionForm questionFormId, String answer){
    public QuestionAnswerDto(QuestionAnswer data){

        this(data.getId(), data.getUser(), data.getQuestionForm(), data.getAnswer());
    }
}
