package com.reviewer.reviewer.dto.forms;
import com.reviewer.reviewer.dto.questions.QuestionsByIdDto;
import com.reviewer.reviewer.models.QuestionForm;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record QuestionFormCreatedDto(Long id, String title, @NotBlank List<QuestionsByIdDto> questions, @NotNull int year) {
    public QuestionFormCreatedDto(List<QuestionsByIdDto> questions, QuestionForm formQuestion) {
        this(formQuestion.getForm().getId(), formQuestion.getForm().getTitle(), questions, formQuestion.getForm().getYear());
    }
   
}

