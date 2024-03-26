package com.reviewer.reviewer.dto.forms;
import com.reviewer.reviewer.dto.questions.QuestionsByIdDto;
import com.reviewer.reviewer.models.QuestionForm;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record QuestionFormCreateDto(Long id, @NotBlank List<QuestionsByIdDto> questions, @NotNull LocalDate year) {
    public QuestionFormCreateDto(List<QuestionsByIdDto> questions, QuestionForm formQuestion) {
        this(formQuestion.getForm().getId(),questions, formQuestion.getForm().getYear());
    }
   
}

