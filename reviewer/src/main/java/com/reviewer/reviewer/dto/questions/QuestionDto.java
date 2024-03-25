package com.reviewer.reviewer.dto.questions;
import com.reviewer.reviewer.models.Question;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record QuestionDto(

        @NotBlank
        @Pattern(regexp = "^[^0-9]*[a-zA-ZÀ-ú.,!?\\s][^0-9]*$", message = "O campo 'question' só pode conter letras e espaços")
        String question,

        Boolean active
) {
    public QuestionDto(Question question) {
        this(question.getQuestion(), question.getActive());
    }
}
