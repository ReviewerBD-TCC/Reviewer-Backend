package com.reviewer.reviewer.dto.questions;
import com.reviewer.reviewer.models.Question;
import jakarta.validation.constraints.NotBlank;

public record QuestionDto(

        @NotBlank
        String questionPt,
        @NotBlank
        String questionEn,
        Boolean active
) {
    public QuestionDto(Question question) {
        this(question.getQuestionPt(),question.getQuestionEn(), question.getActive());
    }
}
