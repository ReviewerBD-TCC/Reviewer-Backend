package com.reviewer.reviewer.dto.questions;

import com.reviewer.reviewer.models.QuestionAnswer;
import jakarta.validation.constraints.NotNull;

public record QuestionAndAnswerDto(
        @NotNull
        Long question,

        AnswerDto answer
) {
}
