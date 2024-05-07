package com.reviewer.reviewer.dto.questions;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record QuestionAnswerDto (

        @NotNull
        Long questionFormId,

        List<QuestionAndAnswerDto> questionAnswer

){

}