package com.reviewer.reviewer.dto.questions;
import com.reviewer.reviewer.models.Question;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record QuestionDto(

        @NotBlank
        @Pattern(regexp = "^[^0-9]*[a-zA-ZÀ-ú.,!?\\s][^0-9]*$", message = "O campo 'question' só pode conter letras e espaços")
        String questionPt,
        @NotBlank
        @Pattern(regexp = "^[^0-9]*[a-zA-ZÀ-ú.,!?\\s][^0-9]*$", message = "O campo 'question' só pode conter letras e espaços")
        String questionEn,
        Boolean active
) {
    public QuestionDto(Question question) {
        this(question.getQuestionPt(),question.getQuestionEn(), question.getActive());
    }
}
