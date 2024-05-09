package com.reviewer.reviewer.dto.questions;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record QuestionAnswerDto (

        @NotNull
        Long questionFormId,
        @NotNull
        Long userId,

        List<QuestionAndAnswerDto> questionAnswer

     

){

}