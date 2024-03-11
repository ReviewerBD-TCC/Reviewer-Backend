package com.reviewer.reviewer.dto.forms;

import java.time.LocalDate;
public record FormQuestionDto(Long formId, String question, LocalDate year) {
} 
    

