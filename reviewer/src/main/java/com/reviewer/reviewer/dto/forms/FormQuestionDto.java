package com.reviewer.reviewer.dto.forms;
import com.reviewer.reviewer.dto.questions.QuestionsByIdDto;
import com.reviewer.reviewer.models.FormQuestion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record FormQuestionDto(Long id, @NotBlank List<QuestionsByIdDto> questions, @NotNull LocalDate year) {
    public FormQuestionDto(List<QuestionsByIdDto> questions, FormQuestion formQuestion) {
        this(formQuestion.getForm().getId(),questions, formQuestion.getForm().getYear());
    }
   
}

