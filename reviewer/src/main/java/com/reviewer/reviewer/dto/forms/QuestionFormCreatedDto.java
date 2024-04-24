package com.reviewer.reviewer.dto.forms;
import com.reviewer.reviewer.dto.questions.QuestionResponseDto;
import com.reviewer.reviewer.models.QuestionForm;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record QuestionFormCreatedDto(Long id, String title, @NotBlank List<QuestionResponseDto> questions, @NotNull LocalDate year) {
    public QuestionFormCreatedDto(List<QuestionResponseDto> questions, QuestionForm formQuestion) {
        this(formQuestion.getForm().getId(), formQuestion.getForm().getTitle(), questions, formQuestion.getForm().getYear());
    }
   
}

