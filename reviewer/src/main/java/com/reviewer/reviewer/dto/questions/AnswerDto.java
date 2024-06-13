package com.reviewer.reviewer.dto.questions;

import jakarta.validation.constraints.NotNull;

public record AnswerDto(

        @NotNull
        String answer
) {


}
