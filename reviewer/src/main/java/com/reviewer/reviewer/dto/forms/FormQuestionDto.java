package com.reviewer.reviewer.dto.forms;

import com.reviewer.reviewer.models.FormQuestion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
public record FormQuestionDto(

        @NotBlank
        String question,

        @NotNull
        LocalDate year
) {
    public FormQuestionDto(FormQuestion formQuestion) {
        this(formQuestion.getQuestion().getQuestion(), formQuestion.getForm().getYear());
    }
}
    

