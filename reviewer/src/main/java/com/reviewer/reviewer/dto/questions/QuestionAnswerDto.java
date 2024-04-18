package com.reviewer.reviewer.dto.questions;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record QuestionAnswerDto (

        @NotNull
        Long userId,

        @NotNull
        Long questionFormId,

      
        String []answers
){
    
}