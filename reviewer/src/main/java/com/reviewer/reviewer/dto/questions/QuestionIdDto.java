package com.reviewer.reviewer.dto.questions;

import jakarta.validation.constraints.NotNull;

public record QuestionIdDto(
        @NotNull
        Long question
) {
}
