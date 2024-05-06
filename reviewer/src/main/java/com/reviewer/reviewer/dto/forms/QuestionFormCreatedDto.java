package com.reviewer.reviewer.dto.forms;
import com.reviewer.reviewer.dto.questions.QuestionDto;
import com.reviewer.reviewer.dto.questions.QuestionIdDto;
import com.reviewer.reviewer.dto.questions.QuestionResponseDto;
import com.reviewer.reviewer.models.Question;
import com.reviewer.reviewer.models.QuestionForm;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record QuestionFormCreatedDto(

        String title,

        @NotNull
        List<QuestionIdDto> questions,

        @NotNull LocalDate year
) {
}

