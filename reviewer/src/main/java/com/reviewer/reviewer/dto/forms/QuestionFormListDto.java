package com.reviewer.reviewer.dto.forms;
import com.reviewer.reviewer.dto.questions.QuestionResponseDto;
import com.reviewer.reviewer.models.Question;

import java.time.LocalDate;
import java.util.List;



public record QuestionFormListDto(Long id, String title, LocalDate year, List<QuestionResponseDto> questions) {
    
}
