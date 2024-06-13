package com.reviewer.reviewer.dto.questions;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record QuestionAnswerDto (

        @NotNull
        Long questionFormId,

        @NotNull
        String whoAnsweredId,
        @NotNull String forWhichUser,

        List<QuestionAndAnswerDto> questionAnswer

){

}