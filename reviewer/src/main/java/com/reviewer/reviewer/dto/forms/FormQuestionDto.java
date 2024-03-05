package com.reviewer.reviewer.dto.forms;

import java.time.LocalDate;
import java.time.LocalDateTime;
public record FormQuestionDto(Long formId, String question, LocalDate year) {
} 
    

