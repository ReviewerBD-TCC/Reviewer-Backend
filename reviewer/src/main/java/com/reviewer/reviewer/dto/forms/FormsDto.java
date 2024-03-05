package com.reviewer.reviewer.dto.forms;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.reviewer.reviewer.dto.questions.QuestionDto;

public record FormsDto(Long id, LocalDate year,  LocalDate validation) {
}
