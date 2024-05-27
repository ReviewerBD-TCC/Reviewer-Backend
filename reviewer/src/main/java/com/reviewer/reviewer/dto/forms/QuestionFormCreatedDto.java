package com.reviewer.reviewer.dto.forms;
import com.reviewer.reviewer.dto.questions.QuestionIdDto;
import com.reviewer.reviewer.models.Indicated;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record QuestionFormCreatedDto(

        String title,

        Long indication,

        @NotNull
        List<QuestionIdDto> questions,

        @NotNull LocalDate year
) {
}

