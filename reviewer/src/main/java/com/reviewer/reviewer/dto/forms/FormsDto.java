package com.reviewer.reviewer.dto.forms;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.reviewer.reviewer.dto.questions.QuestionDto;
import jakarta.validation.constraints.NotNull;


public record FormsDto(

        @NotNull
        LocalDate year,

        @NotNull
        LocalDate validation
) {

}
